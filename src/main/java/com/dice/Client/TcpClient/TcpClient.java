package com.dice.Client.TcpClient;

import com.dice.Exceptions.DiceDbException;

import java.util.concurrent.BlockingQueue;

public interface TcpClient {
  public TcpResponse sendSync(byte[] data) throws InterruptedException, DiceDbException;

  public BlockingQueue<TcpResponse> sendAsync(byte[] data) throws DiceDbException;

  public boolean isHealthy();

  public void close();
}
