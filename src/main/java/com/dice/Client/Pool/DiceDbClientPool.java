package com.dice.Client.Pool;

import com.dice.Client.DiceDbClient.DiceDbClient;
import com.dice.Client.DiceDbClient.DiceDbClientFactory;
import com.dice.Command.CommandProto.Command;
import com.dice.Exceptions.NoConnectionLeftInPoolException;
import com.dice.Reponse.Response;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class DiceDbClientPool implements ClientPool {

  private static final Logger logger = Logger.getLogger(DiceDbClientPool.class.getName());
  private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
  private final int EVICTION_INTERVAL_IN_SECONDS = 10;

  private final ReentrantLock lock = new ReentrantLock();

  int maxPoolSize;
  int minPoolSize;
  int totalConnections;
  LinkedListPool connectionPool;
  DiceDbClientFactory clientFactory;

  public DiceDbClientPool(String host, int port, int minPoolSize, int maxPoolSize) {
    this.totalConnections = 0;
    this.minPoolSize = minPoolSize;
    this.maxPoolSize = maxPoolSize;
    this.connectionPool = new LinkedListPool(maxPoolSize);
    this.clientFactory = new DiceDbClientFactory(host, port);

    for (int i = 0; i < minPoolSize; i++) {
      try {
        connectionPool.put(this.clientFactory.createClient());
        this.totalConnections++;
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
    this.scheduleEvictionJob();
  }

  @Override
  public Response fire(Command command) throws Exception {
    DiceDbClient client = this.getConnection();
    Response resp = client.fire(command);
    this.connectionPool.put(client);
    return resp;
  }

  @Override
  public Response fire(String cmd, List<String> args) throws Exception {
    DiceDbClient client = this.getConnection();
    Response resp = client.fire(cmd, args);
    this.returnConnection(client);
    return resp;
  }

  @Override
  public DiceDbClient getConnection() throws Exception {
    this.lock.lock();
    DiceDbClient client = null;
    try {
      if (this.connectionPool.isEmpty()) {
        if (this.totalConnections <= this.maxPoolSize) {
          this.connectionPool.put(this.clientFactory.createClient());
          this.totalConnections++;
        } else {
          throw new NoConnectionLeftInPoolException();
        }
      }
      client = this.connectionPool.get();
      if (!client.isHealthy()) {
        client.close();
        client = this.clientFactory.createClient();
      }
      return client;
    } finally {
      this.lock.unlock();
    }
  }

  @Override
  public void returnConnection(DiceDbClient client) throws Exception {
    this.lock.lock();
    try {
      if (client.isHealthy()) {
        this.connectionPool.put(client);
      } else {
        client.close();
        this.totalConnections--;
      }
    } finally {
      this.lock.unlock();
    }
  }

  @Override
  public void evict() throws Exception {
    int connectionsEvicted = 0;
    while (this.totalConnections > this.minPoolSize) {
      DiceDbClient client = this.connectionPool.get();
      client.close();
      this.totalConnections--;
      connectionsEvicted++;
    }

    if (connectionsEvicted > 0) {
      logger.info("Evicted " + connectionsEvicted + " connections from the pool.");
    }
  }

  @Override
  public void fillUp() throws Exception {
    int connectionsCreated = 0;
    while (this.totalConnections < this.minPoolSize) {
      this.connectionPool.put(this.clientFactory.createClient());
      this.totalConnections++;
      connectionsCreated++;
    }

    if (connectionsCreated > 0) {
      logger.info("Filled up the pool with " + connectionsCreated + " connections.");
    }
  }

  private void scheduleEvictionJob() {
    scheduler.scheduleAtFixedRate(() -> {
      try {
        evict();
        fillUp();
      } catch (Exception e) {
        logger.warning("Error during eviction: " + e.getMessage());
      }
    }, 0, EVICTION_INTERVAL_IN_SECONDS, TimeUnit.SECONDS);
  }

  @Override
  public void close() throws Exception {
    scheduler.shutdown();
    while (this.totalConnections > 0) {
      DiceDbClient client = this.connectionPool.get();
      client.close();
      this.totalConnections--;
    }
    logger.info("All connections closed.");
  }
}
