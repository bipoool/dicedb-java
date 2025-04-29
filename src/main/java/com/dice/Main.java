package com.dice;

import com.dice.Client.DiceDbClient.SimpleDiceDbClient;
import com.dice.Reponse.Response;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        SimpleDiceDbClient dice = new SimpleDiceDbClient("localhost", 7379);
        dice.connect();
        dice.fire("PING", null);
        dice.fire("SET", List.of("key", "value"));
        Response response = dice.fire("GET", List.of("key"));
        System.out.println("Response: " + response);

        Thread.sleep(10000);
        dice.fire("PING", null);
//        dice.close();
    }
}