package com.dice.Client.Pool;

import com.dice.Client.DiceDbClient.DiceDbClient;
import com.dice.Client.DiceDbClient.DiceDbClientFactory;
import com.dice.Command.CommandProto;
import com.dice.Command.CommandProto.Command;
import com.dice.Exceptions.DiceDbException;
import com.dice.Reponse.Response;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DiceDbClientPool implements ClientPool {

  private static final Logger logger = LoggerFactory.getLogger(DiceDbClientPool.class.getName());
  private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
  private final int JOB_INTERVAL_IN_SECONDS = 10;

  private final ReentrantLock lock = new ReentrantLock();

  int maxPoolSize;
  int minPoolSize;
  int totalConnections;
  LinkedListPool connectionPool;
  DiceDbClientFactory clientFactory;

  public DiceDbClientPool(String host, int port, int minPoolSize, int maxPoolSize)
      throws DiceDbException {
    this.totalConnections = 0;
    this.minPoolSize = minPoolSize;
    this.maxPoolSize = maxPoolSize;
    this.connectionPool = new LinkedListPool(maxPoolSize);
    this.clientFactory = new DiceDbClientFactory(host, port);

    for (int i = 0; i < minPoolSize; i++) {
      connectionPool.put(this.clientFactory.createClient());
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
    CommandProto.Command commandProto = CommandProto.Command
        .newBuilder()
        .setCmd(cmd)
        .addAllArgs(args)
        .build();
    return this.fire(commandProto);
  }

  @Override
  public BlockingQueue<Response> watch(String cmd, List<String> args) throws DiceDbException {
    CommandProto.Command commandProto = CommandProto.Command
        .newBuilder()
        .setCmd(cmd)
        .addAllArgs(args)
        .build();
    return this.watch(commandProto);
  }

  @Override
  public BlockingQueue<Response> watch(Command command) throws DiceDbException {
    DiceDbClient client = this.getConnection();
    BlockingQueue<Response> resp = client.watch(command, () -> this.returnConnection(client));
    this.returnConnection(client);
    return resp;
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
      logger.debug("Filled up the pool with " + connectionsCreated + " connections.");
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
    while (!(this.totalConnections == 0 && this.connectionPool.getSize() == 0)) {
      DiceDbClient client = this.connectionPool.get();
      client.close();
      this.totalConnections--;
    }
    logger.info("All connections closed.");
  }
}
