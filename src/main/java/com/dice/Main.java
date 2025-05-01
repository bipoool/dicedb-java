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

        Response response1 = dice.fire("PING", null);
        System.out.println("Response: " + response1);

        Response response2 = dice.fire("SET", List.of("key", "value"));
        System.out.println("Response: " + response2);
        Response response3 = dice.fire("GET", List.of("key1"));
        System.out.println("Response: " + response3.getGETRes().getValue());

//        BlockingQueue<Response> watchResponse = dice.watch("GET.WATCH", List.of("key"));
//        int count = 0;
//        while (count < 10) {
//            Response response = watchResponse.take();
//            System.out.println("Watch Response: " + response);
//            count++;
//        }
        dice.close();

    }
}