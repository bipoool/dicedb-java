package me.vipulgupta.dice.Client.Pool;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import me.vipulgupta.dice.Client.DiceDbClient.DiceDbClient;
import me.vipulgupta.dice.Client.DiceDbClient.DiceDbClientFactory;
import me.vipulgupta.dice.Command.CommandProto.Command;
import me.vipulgupta.dice.Exceptions.DiceDbException;
import me.vipulgupta.dice.Reponse.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DiceDbClientPool implements ClientPool {

  private static final Logger logger = LoggerFactory.getLogger(DiceDbClientPool.class.getName());
  private final int JOB_INTERVAL_IN_SECONDS = 5;

  private final ReentrantLock lock = new ReentrantLock();
  private final ScheduledExecutorService scheduler;
  int maxPoolSize;
  int minPoolSize;
  int totalConnections;
  LinkedListPool connectionPool;
  LinkedListPool watchClients;
  DiceDbClientFactory clientFactory;

  public DiceDbClientPool(String host, int port, int minPoolSize, int maxPoolSize)
      throws DiceDbException {
    this.totalConnections = 0;
    this.minPoolSize = minPoolSize;
    this.maxPoolSize = maxPoolSize;
    this.connectionPool = new LinkedListPool(maxPoolSize);
    this.watchClients = new LinkedListPool(maxPoolSize);
    this.clientFactory = new DiceDbClientFactory(host, port);
    this.scheduler = Executors.newSingleThreadScheduledExecutor();

    for (int i = 0; i < minPoolSize; i++) {
      try {
        this.connectionPool.put(this.clientFactory.createClient());
      } catch (DiceDbException exp) {
        logger.error("Error creating client: {}", exp.getMessage());
        this.close();
        throw exp;
      }
      this.totalConnections++;
    }
    this.scheduleEvictionJob();
  }

  @Override
  public Response fire(Command command) throws DiceDbException {
    DiceDbClient client = this.getConnection();
    Response resp = client.fire(command);
    this.returnConnection(client);
    return resp;
  }

  @Override
  public Response fire(String cmd, List<String> args) throws DiceDbException {
    DiceDbClient client = this.getConnection();
    Response resp = client.fire(cmd, args);
    this.returnConnection(client);
    return resp;
  }

  @Override
  public BlockingQueue<Response> watch(String cmd, List<String> args) throws DiceDbException {
    DiceDbClient client = this.getConnection();
    watchClients.put(client);
    return client.watch(cmd, args, () -> this.returnConnection(client));
  }

  @Override
  public BlockingQueue<Response> watch(Command command) throws DiceDbException {
    DiceDbClient client = this.getConnection();
    watchClients.put(client);
    return client.watch(command, () -> this.returnConnection(client));
  }

  @Override
  public DiceDbClient getConnection() throws DiceDbException {
    this.lock.lock();
    DiceDbClient client = null;
    try {
      if (this.connectionPool.isEmpty()) {
        if (this.totalConnections < this.maxPoolSize) {
          this.connectionPool.put(this.clientFactory.createClient());
          this.totalConnections++;
          logger.debug("Created a new connection. Total connections: {}",
              this.connectionPool.getSize());
        } else {
          throw new DiceDbException("No connection left in pool");
        }
      }
      client = this.connectionPool.get();
      if (!client.isHealthy()) {
        client.close();
        client = this.clientFactory.createClient();
      }
      logger.debug("Got a connection from pool. Total connections: {}",
          this.connectionPool.getSize());
      return client;
    } finally {
      this.lock.unlock();
    }
  }

  @Override
  public void returnConnection(DiceDbClient client) {
    this.lock.lock();
    try {
      if (client.isHealthy()) {
        this.connectionPool.put(client);
        logger.debug("Returned connection to pool. Total connections: {}",
            this.connectionPool.getSize());
      } else {
        client.close();
        this.totalConnections--;
        logger.debug("Connection is unhealthy. Total connections: {}",
            this.connectionPool.getSize());
      }
    } finally {
      this.lock.unlock();
    }
  }

  @Override
  public void evict() {
    int connectionsEvicted = 0;
    while (this.connectionPool.getSize() > this.minPoolSize) {
      DiceDbClient client = this.connectionPool.get();
      client.close();
      this.totalConnections--;
      connectionsEvicted++;
    }

    if (connectionsEvicted > 0) {
      logger.debug("Evicted {} connections from the pool.", connectionsEvicted);
    } else {
      logger.debug("No connections to evict from the pool.");
    }
  }

  @Override
  public void fillUp() throws DiceDbException {
    int connectionsCreated = 0;
    while (this.totalConnections < this.minPoolSize) {
      this.connectionPool.put(this.clientFactory.createClient());
      this.totalConnections++;
      connectionsCreated++;
    }

    if (connectionsCreated > 0) {
      logger.debug("Filled up the pool with {} connections.", connectionsCreated);
    } else {
      logger.debug("No connections needed to be filled up.");
    }
  }

  private void scheduleEvictionJob() {
    scheduler.scheduleAtFixedRate(() -> {
      try {
        evict();
        fillUp();
      } catch (Exception e) {
        logger.warn("Error during eviction: {}", e.getMessage());
      }
    }, 0, JOB_INTERVAL_IN_SECONDS, TimeUnit.SECONDS);
  }

  @Override
  public void close() {
    scheduler.shutdown();
    while (!watchClients.isEmpty()) {
      DiceDbClient client = watchClients.get();
      client.close();
      this.totalConnections--;
    }
    while (!connectionPool.isEmpty()) {
      DiceDbClient client = this.connectionPool.get();
      client.close();
      this.totalConnections--;
    }
    logger.info("All connections closed.");
  }
}
