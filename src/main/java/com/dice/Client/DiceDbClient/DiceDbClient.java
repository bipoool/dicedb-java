package com.dice.Client.DiceDbClient;

import com.dice.Command.CommandProto;
import com.dice.Exceptions.DiceDbException;
import com.dice.Reponse.Response;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public interface DiceDbClient {

    Response fire(CommandProto.Command command) throws DiceDbException;

    Response fire(String cmd, List<String> args) throws DiceDbException;

    BlockingQueue<Response> watch(String cmd, List<String> args) throws DiceDbException;

    BlockingQueue<Response> watch(CommandProto.Command command) throws DiceDbException;

    boolean isHealthy();

    void close();
}
