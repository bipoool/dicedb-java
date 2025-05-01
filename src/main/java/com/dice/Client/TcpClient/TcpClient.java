package com.dice.Client.TcpClient;

import com.dice.Exceptions.DiceDbException;
import java.util.concurrent.BlockingQueue;

public interface TcpClient {

  TcpResponse sendSync(byte[] data) throws InterruptedException, DiceDbException;

  BlockingQueue<TcpResponse> sendAsync(byte[] data) throws DiceDbException;

  boolean isHealthy();

  void close();
}
