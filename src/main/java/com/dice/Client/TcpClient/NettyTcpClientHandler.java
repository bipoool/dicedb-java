package com.dice.Client.TcpClient;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

class NettyTcpClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private static final Logger logger = Logger.getLogger(NettyTcpClientHandler.class.getName());
    private Channel channel;
    private CompletableFuture<byte[]> responseFuture;

    private TcpConnectionStatus tcpConnectionStatus = TcpConnectionStatus.CLOSED;

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        channel = ctx.channel();
        tcpConnectionStatus = TcpConnectionStatus.ACTIVE;
    }

    public byte[] send(byte[] data) throws ExecutionException, InterruptedException {
        if (channel == null || !channel.isActive()) {
            throw new IllegalStateException("Not connected to server!");
        }
        tcpConnectionStatus = TcpConnectionStatus.ACTIVE;
        this.responseFuture = new CompletableFuture<>();

        // First 4 Bytes to send with length of the content
        ByteBuf buf = channel.alloc().buffer(4 + data.length);
        buf.writeInt(data.length);

        buf.writeBytes(data);
        channel.writeAndFlush(buf);

        return this.responseFuture.get();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) {
        tcpConnectionStatus = TcpConnectionStatus.ACTIVE;
        ByteBuf actualProtobuf = msg.slice(4, msg.readableBytes() - 4);
        byte[] protobufBytes = new byte[actualProtobuf.readableBytes()];
        actualProtobuf.readBytes(protobufBytes);
        responseFuture.complete(protobufBytes);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        tcpConnectionStatus = TcpConnectionStatus.ERROR;
        if (responseFuture != null) {
            responseFuture.completeExceptionally(cause);
        }
        cause.printStackTrace();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent e) {
            if (e.state() == IdleState.READER_IDLE || e.state() == IdleState.WRITER_IDLE || e.state() == IdleState.ALL_IDLE) {
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
    }
}