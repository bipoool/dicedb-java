package me.vipulgupta.dice.Client.TcpClient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.util.concurrent.BlockingQueue;
import me.vipulgupta.dice.Exceptions.DiceDbException;

public class NettyTcpClient implements TcpClient {

  EventLoopGroup group;
  NettyTcpClientHandler tcpHandler;

  public NettyTcpClient(String host, int port) throws DiceDbException {

    int CONNECT_TIMEOUT_MILLIS = 5000;

    try {
      this.tcpHandler = new NettyTcpClientHandler();
      this.group = new NioEventLoopGroup();
      Bootstrap bootstrap = new Bootstrap();

      bootstrap.group(this.group)
          .channel(NioSocketChannel.class)
          .option(ChannelOption.SO_KEEPALIVE, true)
          .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, CONNECT_TIMEOUT_MILLIS)
          .handler(new NettyChannelInitializer(tcpHandler));
      bootstrap.connect(host, port).sync();
    } catch (Exception e) {
      this.close();
      throw new DiceDbException("Failed to connect to server: " + e.getMessage(), e);
    }

  }

  @Override
  public TcpResponse sendSync(byte[] data) throws DiceDbException {
    return tcpHandler.sendSync(data);
  }

  @Override
  public BlockingQueue<TcpResponse> sendAsync(byte[] data) throws DiceDbException {
    return tcpHandler.sendAsync(data);
  }

  @Override
  public boolean isHealthy() {
    return this.group != null && !this.group.isShuttingDown() && this.tcpHandler != null
        && tcpHandler.isHealthy();
  }

  @Override
  public void close() {
    if (this.group != null) {
      this.group.shutdownGracefully();
    }
    if (this.tcpHandler != null) {
      this.tcpHandler.close();
    }
  }

}
