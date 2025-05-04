package com.dice.Client.TcpClient;

import com.dice.Exceptions.DiceDbException;
import com.dice.Exceptions.DiceDbTcpException;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyTcpClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

  private static final Logger logger = LoggerFactory.getLogger(NettyTcpClientHandler.class);
  private final BlockingQueue<TcpResponse> responseQueue;
  private Channel channel;
  private TcpConnectionStatus tcpConnectionStatus = TcpConnectionStatus.CLOSED;

  NettyTcpClientHandler() {
    this.responseQueue = new ArrayBlockingQueue<>(1);
  }

  @Override
  public void channelRegistered(ChannelHandlerContext ctx) {
    channel = ctx.channel();
    tcpConnectionStatus = TcpConnectionStatus.ACTIVE;
  }

  public BlockingQueue<TcpResponse> sendAsync(byte[] data) throws DiceDbException {
    this.writeAndFlush(data);
    return responseQueue;
  }

  public TcpResponse sendSync(byte[] data) throws InterruptedException, DiceDbException {
    this.writeAndFlush(data);
    return this.responseQueue.take();
  }

  private void writeAndFlush(byte[] data) throws DiceDbException {
    if (channel == null || !channel.isActive()) {
      throw new DiceDbException("Not connected to server!");
    }
    tcpConnectionStatus = TcpConnectionStatus.ACTIVE;

    // First 4 Bytes to send with length of the content
    ByteBuf buf = channel.alloc().buffer(4 + data.length);
    buf.writeInt(data.length);

    buf.writeBytes(data);
    channel.writeAndFlush(buf);
  }

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws InterruptedException {
    tcpConnectionStatus = TcpConnectionStatus.ACTIVE;
    if (msg.readableBytes() < 4) {
      responseQueue.put(new TcpResponse(new DiceDbTcpException("Received message is too short")));
      logger.warn("Received message is too short: {}", msg.readableBytes());
      return;
    }
    int length = msg.readInt();
    byte[] protobufBytes = new byte[length];
    msg.readBytes(protobufBytes);
    TcpResponse tcpResponse = new TcpResponse(protobufBytes);
    responseQueue.put(tcpResponse);
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
      throws InterruptedException {
    TcpResponse tcpResponse = new TcpResponse(cause);
    responseQueue.put(tcpResponse);
    logger.error("Exception Caught in TCP connection: {}", cause.getMessage());
    this.close();
    tcpConnectionStatus = TcpConnectionStatus.ERROR;
  }

  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    if (evt instanceof IdleStateEvent e) {
      if (e.state() == IdleState.READER_IDLE || e.state() == IdleState.WRITER_IDLE
          || e.state() == IdleState.ALL_IDLE) {
        tcpConnectionStatus = TcpConnectionStatus.IDLE;
      }
    } else {
      super.userEventTriggered(ctx, evt);
    }
  }

  public TcpConnectionStatus getTcpConnectionStatus() {
    return tcpConnectionStatus;
  }

  public boolean isHealthy() {
    return channel != null &&
        channel.isOpen() &&
        channel.isActive() &&
        (tcpConnectionStatus == TcpConnectionStatus.ACTIVE ||
            tcpConnectionStatus == TcpConnectionStatus.IDLE);
  }

  public void close() {
    tcpConnectionStatus = TcpConnectionStatus.CLOSED;
    if (channel != null) {
      channel.close();
    }
    responseQueue.offer(new TcpResponse());
  }
}