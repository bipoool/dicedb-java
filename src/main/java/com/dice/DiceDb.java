package com.dice;

import com.dice.Client.DiceDbClient.DiceDbClient;
import com.dice.Client.DiceDbClient.DiceDbClientFactory;
import com.dice.Command.CommandProto;
import com.dice.Exceptions.NoConnectionLeftInPoolException;
import com.dice.Reponse.Response;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class DiceDb {

    int maxPoolSize;
    int minPoolSize;
    BlockingQueue<DiceDbClient> connectionPool;
    DiceDbClientFactory clientFactory;
    int TotalConnections;

    public DiceDb(String host, int port, int minPoolSize, int maxPoolSize) {

        this.TotalConnections = 0;
        this.minPoolSize = minPoolSize;
        this.maxPoolSize = maxPoolSize;
        this.connectionPool = new LinkedBlockingDeque<>(maxPoolSize);
        this.clientFactory = new DiceDbClientFactory(host, port);

        for (int i = 0; i < minPoolSize; i++) {
            try {
                connectionPool.put(this.clientFactory.createClient());
                this.TotalConnections++;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    public DiceDb(String host, int port, int maxPoolSize) {
        this(host, port, 1, maxPoolSize);
    }

    public DiceDb(String host, int port) {
        this(host, port, 1, 10);
    }

    public Response fire(CommandProto.Command command) throws Exception {
        DiceDbClient client = spinUpClientOrThrowError();
        Response resp = client.fire(command);
        this.connectionPool.put(client);
        return resp;
    }

    public Response fire(String cmd, List<String> args) throws Exception {
        DiceDbClient client = spinUpClientOrThrowError();
        Response resp = client.fire(cmd, args);
        this.connectionPool.put(client);
        return resp;
    }

    private DiceDbClient spinUpClientOrThrowError() throws Exception {
        if (this.connectionPool.isEmpty()) {
            if (this.TotalConnections < this.maxPoolSize) {
                this.connectionPool.put(this.clientFactory.createClient());
                this.TotalConnections++;
            } else {
                throw new NoConnectionLeftInPoolException();
            }
        }
        return this.connectionPool.take();
    }
}
