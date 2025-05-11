package me.vipulgupta.dice.Client.TcpClient;

import java.util.concurrent.BlockingQueue;
import me.vipulgupta.dice.Exceptions.DiceDbException;

public interface TcpClient {

  TcpResponse sendSync(byte[] data) throws DiceDbException;

  BlockingQueue<TcpResponse> sendAsync(byte[] data) throws DiceDbException;

  boolean isHealthy();

  void close();
}
