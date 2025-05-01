package com.dice;

import com.dice.Exceptions.DiceDbException;
import com.dice.Reponse.Response;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Main {

  public static void main(String[] args) throws DiceDbException, InterruptedException {

    DiceDbConnectionManager dice = new DiceDbConnectionManager("localhost", 7379);

    Response response = dice.fire("FLUSHDB", List.of());
    System.out.println("Response: " + response);

    Response setResp = dice.fire("SET", List.of("key", "value"));
    System.out.println("Response: " + setResp.getSETRes());

    Response getResp = dice.fire("GET", List.of("key"));
    System.out.println("Response: " + getResp.getGETRes().getValue());

    Response zAddResp1 = dice.fire("ZADD", List.of("users", "30", "u1"));
    System.out.println("Response: " + zAddResp1.getZADDRes().getCount());

    Response zAddResp2 = dice.fire("ZADD", List.of("users", "50", "u2"));
    System.out.println("Response: " + zAddResp2.getZADDRes().getCount());

    Response zRankResp = dice.fire("ZRANK", List.of("users", "u2"));
    System.out.println("Response: " + zRankResp.getZRANKRes().getElement());

    BlockingQueue<Response> watchResp = dice.watch("GET.WATCH", List.of("key"));

    int count = 0;
    while (count < 10) {
      Response resp = watchResp.take();
      System.out.println("Response: " + resp.getGETRes().getValue());
      count++;
    }
    dice.close();

  }
}
