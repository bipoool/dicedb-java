package com.dice.Client.DiceDbClient;

import com.dice.Exceptions.DiceDbException;

public class DiceDbClientFactory {

  private final String host;
  private final int port;

  public DiceDbClientFactory(String host, int port) {
    this.host = host;
    this.port = port;
  }

  public DiceDbClient createClient() throws DiceDbException {
    return new SimpleDiceDbClient(host, port);
  }
}
