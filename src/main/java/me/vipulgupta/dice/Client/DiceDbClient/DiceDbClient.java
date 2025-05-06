package me.vipulgupta.dice.Client.DiceDbClient;

import me.vipulgupta.dice.Command.CommandProto;
import me.vipulgupta.dice.Exceptions.DiceDbException;
import me.vipulgupta.dice.Reponse.Response;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public interface DiceDbClient {

  Response fire(CommandProto.Command command) throws DiceDbException;

  Response fire(String cmd, List<String> args) throws DiceDbException;

  BlockingQueue<Response> watch(String cmd, List<String> args,
      DiceDbCallBack callBackAtConnectionClose) throws DiceDbException;

  default BlockingQueue<Response> watch(String cmd, List<String> args) throws DiceDbException {
    return watch(cmd, args, null);
  }

  BlockingQueue<Response> watch(CommandProto.Command command,
      DiceDbCallBack callBackAtConnectionClose) throws DiceDbException;

  default BlockingQueue<Response> watch(CommandProto.Command command) throws DiceDbException {
    return watch(command, null);
  }

  boolean isHealthy();

  void close();
}
