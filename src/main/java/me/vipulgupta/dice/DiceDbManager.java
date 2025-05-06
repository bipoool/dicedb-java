package me.vipulgupta.dice;

import me.vipulgupta.dice.Client.Pool.ClientPool;
import me.vipulgupta.dice.Client.Pool.DiceDbClientPool;
import me.vipulgupta.dice.Command.CommandProto;
import me.vipulgupta.dice.Exceptions.DiceDbException;
import me.vipulgupta.dice.Reponse.Response;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class DiceDbManager {

  private final ClientPool clientPool;

  public DiceDbManager(String host, int port, int minConnections, int maxConnections)
      throws DiceDbException {
    this.clientPool = new DiceDbClientPool(host, port, minConnections, maxConnections);
  }

  public DiceDbManager(String host, int port) throws DiceDbException {
    this(host, port, 1, 10);
  }

  public Response fire(CommandProto.Command command) throws DiceDbException {
    return this.clientPool.fire(command);
  }

  public Response fire(String cmd, List<String> args) throws DiceDbException {
    return this.clientPool.fire(cmd, args);
  }

  public BlockingQueue<Response> watch(CommandProto.Command command) throws DiceDbException {
    return this.clientPool.watch(command);
  }

  public BlockingQueue<Response> watch(String cmd, List<String> args) throws DiceDbException {
    return this.clientPool.watch(cmd, args);
  }

  public void close() {
    this.clientPool.close();
  }

}
