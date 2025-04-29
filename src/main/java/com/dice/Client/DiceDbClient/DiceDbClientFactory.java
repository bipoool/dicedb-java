package com.dice.Client.DiceDbClient;

public class DiceDbClientFactory {
    private String host;
    private int port;

    public DiceDbClientFactory(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public DiceDbClient createClient() throws Exception {
        DiceDbClient client = new SimpleDiceDbClient(host, port);
        client.connect();
        return client;
    }
}
