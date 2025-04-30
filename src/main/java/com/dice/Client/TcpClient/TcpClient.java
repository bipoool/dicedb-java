package com.dice.Client.TcpClient;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;

public interface TcpClient {
  public TcpResponse sendSync(byte[] data) throws ExecutionException, InterruptedException;

  public BlockingQueue<TcpResponse> sendAsync(byte[] data) throws ExecutionException, InterruptedException;

  public boolean isHealthy();

  public void close();
}
