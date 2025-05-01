package com.dice.Client.Pool;

import com.dice.Client.DiceDbClient.DiceDbClient;
import com.dice.Command.CommandProto;
import com.dice.Exceptions.DiceDbException;
import com.dice.Reponse.Response;
import java.util.List;

public interface ClientPool {

  Response fire(CommandProto.Command command) throws DiceDbException;

  Response fire(String cmd, List<String> args) throws DiceDbException;

  //  BlockingQueue<Response> watch(String cmd, List<String> args) throws DiceDbException;
//  BlockingQueue<Response> watch(CommandProto.Command command) throws DiceDbException;
  DiceDbClient getConnection() throws DiceDbException;

  void returnConnection(DiceDbClient client);

  void evict();

  void fillUp() throws DiceDbException;

  void close();
}
