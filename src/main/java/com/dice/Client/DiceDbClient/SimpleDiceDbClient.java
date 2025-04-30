package com.dice.Client.DiceDbClient;

import com.dice.Client.TcpClient.NettyTcpClient;
import com.dice.Client.TcpClient.TcpClient;
import com.dice.Command.CommandProto;
import com.dice.Reponse.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.dice.Reponse.Status.Status_ERR;

public class SimpleDiceDbClient implements DiceDbClient {

    private static final Logger logger = Logger.getLogger(SimpleDiceDbClient.class.getName());
    TcpClient tcpClient;
    String host;
    int port;
    String clientId;

    public SimpleDiceDbClient(String host, int port) {
        this.host = host;
        this.port = port;
        this.clientId = UUID.randomUUID().toString();
    }

    @Override
    public void connect() throws Exception {
        this.tcpClient = new NettyTcpClient(host, port);
        Response resp = this.fire(getHandShakeCommand());
        if (resp.getStatus() == Status_ERR) {
            throw new Exception("Could not complete the handshake: " + resp.getMessage());
        }
        logger.log(Level.INFO, "Connected to server: " + host + ":" + port);
    }

    @Override
    public Response fire(CommandProto.Command command) throws Exception {
        if (tcpClient == null) {
            throw new IllegalStateException("Not connected to server! Please call connect() first.");
        }

        byte[] data = command.toByteArray();
        byte[] response = tcpClient.send(data);
        return Response.parseFrom(response);
    }

    @Override
    public Response fire(String cmd, List<String> args) throws Exception {
        if (args == null || args.isEmpty()) {
            args = new ArrayList<>();
        }

        CommandProto.Command commandProto = CommandProto.Command
                .newBuilder()
                .setCmd(cmd)
                .addAllArgs(args)
                .build();
        return this.fire(commandProto);
    }

    @Override
    public boolean isHealthy() {
        return this.tcpClient != null && this.tcpClient.isHealthy();
    }

    private CommandProto.Command getHandShakeCommand() {
        if (this.clientId == null) {
            this.clientId = UUID.randomUUID().toString();
        }
        return CommandProto.Command.newBuilder()
                .setCmd("HANDSHAKE")
                .addArgs(clientId)
                .addArgs("command")
                .build();
    }

    public String getClientId() {
        return clientId;
    }

     @Override
    public void close() {
        if (this.tcpClient != null) {
            this.tcpClient.close();
        }
    }

}
