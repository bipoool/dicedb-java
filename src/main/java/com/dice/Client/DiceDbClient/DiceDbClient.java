package com.dice.Client.DiceDbClient;

import com.dice.Command.CommandProto;
import com.dice.Reponse.Response;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public interface DiceDbClient {
    void connect() throws Exception;

    Response fire(CommandProto.Command command) throws Exception;

    Response fire(String cmd, List<String> args) throws Exception;

    BlockingQueue<Response> watch(String cmd, List<String> args) throws Exception;

    BlockingQueue<Response> watch(CommandProto.Command command) throws Exception;

    boolean isHealthy();

    void close() throws Exception;
}
