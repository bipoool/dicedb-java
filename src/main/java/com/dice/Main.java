package com.dice;

import com.dice.Client.DiceDbClient.DiceDbClient;
import com.dice.Client.DiceDbClient.SimpleDiceDbClient;
import com.dice.Command.CommandProto;
import com.dice.Reponse.Response;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Main {

    public static void main(String[] args) throws Exception {

        DiceDbClient dice = new SimpleDiceDbClient("localhost", 7379);
        dice.connect();
        Response response1 = dice.fire("PING", null);
        System.out.println("Response: " + response1);

        CommandProto.Command command = CommandProto.Command.newBuilder()
                .setCmd("GET.WATCH")
                .addArgs("key")
                .build();
        BlockingQueue<Response> response3 = dice.watch(command);

        int count = 0;
        while (count < 3) {
            Response resp = response3.take();
            System.out.println("Response: " + resp);
            count++;
        }

        dice.close();

    }
}