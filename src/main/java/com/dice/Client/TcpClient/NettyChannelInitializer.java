package com.dice.Client.TcpClient;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import java.util.concurrent.TimeUnit;

public class NettyChannelInitializer extends ChannelInitializer<SocketChannel> {


  private final NettyTcpClientHandler tcpHandler;
  int TIMEOUT_MILLIS = 5000;

  public NettyChannelInitializer(NettyTcpClientHandler tcpHandler) {
    this.tcpHandler = tcpHandler;
  }

  @Override
  protected void initChannel(SocketChannel ch) {
    ChannelPipeline p = ch.pipeline();
    p.addLast(new IdleStateHandler(TIMEOUT_MILLIS, TIMEOUT_MILLIS, TIMEOUT_MILLIS,
        TimeUnit.MILLISECONDS));
    p.addLast(tcpHandler);
  }

}
