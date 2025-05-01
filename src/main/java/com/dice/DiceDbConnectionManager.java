package com.dice;

import com.dice.Client.DiceDbClient.DiceDbClient;
import com.dice.Client.DiceDbClient.SimpleDiceDbClient;
import com.dice.Command.CommandProto;
import com.dice.Exceptions.DiceDbException;
import com.dice.Reponse.Response;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

public class DiceDbConnectionManager {

  private static final Logger logger = Logger.getLogger(DiceDbConnectionManager.class.getName());
  private final DiceDbClient client;

  public DiceDbConnectionManager(String host, int port) throws DiceDbException {
    this.client = new SimpleDiceDbClient(host, port);
  }

  public Response fire(CommandProto.Command command) throws DiceDbException {
    return this.client.fire(command);
  }

  public Response fire(String cmd, List<String> args) throws DiceDbException {
    return this.client.fire(cmd, args);
  }

  public BlockingQueue<Response> watch(CommandProto.Command command) throws DiceDbException {
    return this.client.watch(command);
  }

  public BlockingQueue<Response> watch(String cmd, List<String> args) throws DiceDbException {
    return this.client.watch(cmd, args);
  }

  public boolean isHealthy() {
    return this.client.isHealthy();
  }

  public void close() {
    this.client.close();
  }

}
