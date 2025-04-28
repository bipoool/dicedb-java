package com.dice.Client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.lang.ref.Cleaner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class Client {

    EventLoopGroup group;
    ClientHandler handler;

    public static Client connect(String host, int port) throws InterruptedException {

        Client client = new Client();
        client.handler = new ClientHandler();
        client.group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(client.group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(client.handler);
                    }
                });
        bootstrap.connect(host, port).sync();
        return client;

    }

    public byte[] send(byte[] data) throws ExecutionException, InterruptedException, TimeoutException {
        return handler.send(data);
    }

    public void close() {
        if (this.group != null) {
            this.group.shutdownGracefully();
        }
        if (this.handler != null) {
            this.handler.();
        }
    }

}
