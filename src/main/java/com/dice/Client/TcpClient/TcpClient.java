package com.dice.Client.TcpClient;

import java.util.concurrent.ExecutionException;

public interface TcpClient {
  public byte[] send(byte[] data) throws ExecutionException, InterruptedException;
  public boolean isHealthy();
  public void close();
}
