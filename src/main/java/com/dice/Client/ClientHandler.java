package com.dice.Client;

import com.dice.Reponse.Response;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class ClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private int MAX_RETRIES = 5;
    private int TIME_OUT_IN_SECONDS = 5;

    private Channel channel;
    private CompletableFuture<byte[]> responseFuture;

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        channel = ctx.channel();
    }

    public byte[] send(byte[] data) throws ExecutionException, InterruptedException, TimeoutException {
        if (channel == null || !channel.isActive()) {
            throw new IllegalStateException("Not connected to server!");
        }

        this.responseFuture = new CompletableFuture<>();

        // First 4 Bytes to send with length of the content
        ByteBuf buf = channel.alloc().buffer(4 + data.length);
        buf.writeInt(data.length);

        buf.writeBytes(data);
        channel.writeAndFlush(buf);

        return this.responseFuture.get(5, TimeUnit.SECONDS);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        ByteBuf actualProtobuf = msg.slice(4, msg.readableBytes() - 4);
        byte[] protobufBytes = new byte[actualProtobuf.readableBytes()];
        actualProtobuf.readBytes(protobufBytes);
        responseFuture.complete(protobufBytes);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        if (responseFuture != null) {
            responseFuture.completeExceptionally(cause);
        }
        cause.printStackTrace();
        if (channel != null) {
            channel.close();
        }
    }
}