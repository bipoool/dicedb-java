package com.dice.Client.TcpClient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class NettyTcpClient implements TcpClient {

    EventLoopGroup group;
    NettyTcpClientHandler tcpHandler;

    public NettyTcpClient(String host, int port) throws InterruptedException {

        int CONNECT_TIMEOUT_MILLIS = 5000;
        int READ_TIMEOUT_MILLIS = 5000;

        this.tcpHandler = new NettyTcpClientHandler();
        this.group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(this.group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, CONNECT_TIMEOUT_MILLIS)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(new IdleStateHandler(READ_TIMEOUT_MILLIS, 0, 0, TimeUnit.MILLISECONDS));
                        p.addLast(tcpHandler);
                    }
                });
        bootstrap.connect(host, port).sync();
    }

    @Override
    public TcpResponse sendSync(byte[] data) throws ExecutionException, InterruptedException {
        return tcpHandler.sendSync(data);
    }

    @Override
    public BlockingQueue<TcpResponse> sendAsync(byte[] data) throws ExecutionException, InterruptedException {
        return tcpHandler.sendAsync(data);
    }

    @Override
    public boolean isHealthy() {
        return this.group != null && !this.group.isShuttingDown() && this.tcpHandler != null && tcpHandler.isHealthy();
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
