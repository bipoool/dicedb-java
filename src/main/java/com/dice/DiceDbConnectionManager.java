package com.dice;

import com.dice.Client.Pool.ClientPool;
import com.dice.Client.Pool.DiceDbClientPool;
import com.dice.Command.CommandProto;
import com.dice.Reponse.Response;

import java.util.List;
import java.util.logging.Logger;

public class DiceDbConnectionManager {

  private static final Logger logger = Logger.getLogger(DiceDbConnectionManager.class.getName());
  private static final int DEFAULT_MIN_POOL_SIZE = 1;
  private static final int DEFAULT_MAX_POOL_SIZE = 10;

  private  final ClientPool clientPool;

  public DiceDbConnectionManager(String host, int port, int minPoolSize, int maxPoolSize) {
    this.clientPool = new DiceDbClientPool(host, port, minPoolSize, maxPoolSize);
  }

  public DiceDbConnectionManager(String host, int port) {
    this(host, port, DEFAULT_MIN_POOL_SIZE, DEFAULT_MAX_POOL_SIZE);
  }

  public Response fire(CommandProto.Command command) throws Exception {
    return this.clientPool.fire(command);
  }

  public Response fire(String cmd, List<String> args) throws Exception {
    return this.clientPool.fire(cmd, args);
  }

  public void close() throws Exception {
    this.clientPool.close();
  }

}
