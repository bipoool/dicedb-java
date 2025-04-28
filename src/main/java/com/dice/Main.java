package com.dice;

import com.dice.Client.Client;
import com.dice.Command.CommandProto;
import com.dice.Reponse.Response;
import io.netty.buffer.ByteBuf;

import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Client client = Client.connect("localhost", 7379);
        CommandProto.Command command = CommandProto.Command.newBuilder()
                .setCmd("PING")
                .addArgs("Hello")
                .build();

        byte[] data = command.toByteArray();
        try {
            byte[] response = client.send(data);
            Response result = Response.parseFrom(response);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }
}