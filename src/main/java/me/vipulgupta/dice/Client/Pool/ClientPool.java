package me.vipulgupta.dice.Client.Pool;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import me.vipulgupta.dice.Client.DiceDbClient.DiceDbClient;
import me.vipulgupta.dice.Command.CommandProto;
import me.vipulgupta.dice.Exceptions.DiceDbException;
import me.vipulgupta.dice.Reponse.Response;

public interface ClientPool {

  Response fire(CommandProto.Command command) throws DiceDbException;

  Response fire(String cmd, List<String> args) throws DiceDbException;

  BlockingQueue<Response> watch(String cmd, List<String> args) throws DiceDbException;

  BlockingQueue<Response> watch(CommandProto.Command command) throws DiceDbException;

  DiceDbClient getConnection() throws DiceDbException;

  void returnConnection(DiceDbClient client);

  void evict();

  void fillUp() throws DiceDbException;

  void close();
}
