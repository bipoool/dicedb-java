package me.vipulgupta.dice.Client.TcpClient;

import me.vipulgupta.dice.Exceptions.DiceDbException;
import java.util.concurrent.BlockingQueue;

public interface TcpClient {

  TcpResponse sendSync(byte[] data) throws InterruptedException, DiceDbException;

  BlockingQueue<TcpResponse> sendAsync(byte[] data) throws DiceDbException;

  boolean isHealthy();

  void close();
}
