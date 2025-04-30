package com.dice;

import com.dice.Reponse.Response;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        DiceDbConnectionManager diceDbConnectionManager = new DiceDbConnectionManager("localhost", 7379, 1, 10);
        Response response1 = diceDbConnectionManager.fire("PING", null);
        System.out.println("Response: " + response1);

        Thread.sleep(5000);

        Response response2 = diceDbConnectionManager.fire("SET", List.of("Deepti", "Dalakoti"));
        System.out.println("Response: " + response2);

        Thread.sleep(5000);

        Response response3 = diceDbConnectionManager.fire("GET", List.of("Deepti"));
        System.out.println("Response: " + response3);

        Thread.sleep(5000);

        diceDbConnectionManager.close();

    }
}