package com.dice.Client.Pool;

import com.dice.Client.DiceDbClient.DiceDbClient;
import com.dice.Command.CommandProto;
import com.dice.Reponse.Response;
import java.util.List;

public interface ClientPool {
  Response fire(CommandProto.Command command) throws Exception;
  Response fire(String cmd, List<String> args) throws Exception;
  DiceDbClient getConnection() throws Exception;
  void returnConnection(DiceDbClient client) throws Exception;
  void evict() throws Exception;
  void fillUp() throws Exception;
  void close() throws Exception;
}
