package me.vipulgupta.dice.Client.DiceDbClient;

import static me.vipulgupta.dice.Reponse.Status.Status_ERR;

import me.vipulgupta.dice.Client.TcpClient.NettyTcpClient;
import me.vipulgupta.dice.Client.TcpClient.TcpClient;
import me.vipulgupta.dice.Client.TcpClient.TcpResponse;
import me.vipulgupta.dice.Command.CommandProto;
import me.vipulgupta.dice.Exceptions.DiceDbException;
import me.vipulgupta.dice.Reponse.Response;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleDiceDbClient implements DiceDbClient {

  private static final Logger logger = LoggerFactory.getLogger(SimpleDiceDbClient.class);
  private final ScheduledExecutorService scheduler;
  BlockingQueue<Response> watchResponseQueue;
  TcpClient tcpClient;
  String host;
  int port;
  String clientId;

  public SimpleDiceDbClient(String host, int port) throws DiceDbException {
    this.host = host;
    this.port = port;
    this.clientId = UUID.randomUUID().toString();
    this.scheduler = Executors.newSingleThreadScheduledExecutor();
    this.init();
  }

  private void init() throws DiceDbException {
    try {
      this.tcpClient = new NettyTcpClient(host, port);
      Response resp = this.fire(this.getHandShakeCommand("command"));
      if (resp.getStatus() == Status_ERR) {
        throw new DiceDbException(resp.getMessage());
      }
      logger.info("Connected to server: {}:{}", host, port);
    } catch (InterruptedException e) {
      throw new DiceDbException("Thread interrupted while connecting to server", e);
    }
  }

  @Override
  public Response fire(CommandProto.Command command) throws DiceDbException {
    if (tcpClient == null) {
      throw new DiceDbException("Not connected to server! Please call connect() first.");
    }
    try {
      byte[] data = command.toByteArray();
      TcpResponse tcpResponse = tcpClient.sendSync(data);

      if (tcpResponse.isError) {
        throw new DiceDbException("Error while sending command: " + tcpResponse.exception);
      }
      byte[] response = tcpResponse.data;
      Thread.sleep(2000);
      return Response.parseFrom(response);
    } catch (InterruptedException e) {
      throw new DiceDbException("Thread interrupted while sending command", e);
    } catch (InvalidProtocolBufferException e) {
      throw new DiceDbException("Failed to parse response from server", e);
    } catch (Exception e) {
      throw new DiceDbException("Unexpected error occurred", e);
    }
  }

  @Override
  public Response fire(String cmd, List<String> args) throws DiceDbException {
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
  public BlockingQueue<Response> watch(String cmd, List<String> args, DiceDbCallBack callBack)
      throws DiceDbException {
    if (args == null || args.isEmpty()) {
      args = new ArrayList<>();
    }

    CommandProto.Command commandProto = CommandProto.Command
        .newBuilder()
        .setCmd(cmd)
        .addAllArgs(args)
        .build();
    return this.watch(commandProto);
  }

  @Override
  public BlockingQueue<Response> watch(CommandProto.Command command,
      DiceDbCallBack callBackAtConnectionClose) throws DiceDbException {
    if (tcpClient == null) {
      throw new DiceDbException("Not connected to server! Please call connect() first.");
    }
    if (this.watchResponseQueue != null) {
      return this.watchResponseQueue;
    }
    this.watchResponseQueue = new LinkedBlockingQueue<>();

    Response watchResponse = this.fire(command);
    if (watchResponse.getStatus() == Status_ERR) {
      throw new DiceDbException("Error while sending watch command: " + watchResponse.getMessage());
    }

    CommandProto.Command watchHandShakeCommand = getHandShakeCommand("watch");
    BlockingQueue<TcpResponse> watchTcpResponseQueue = tcpClient.sendAsync(
        watchHandShakeCommand.toByteArray());
    spinUpWatchThread(watchTcpResponseQueue, callBackAtConnectionClose);
    return this.watchResponseQueue;
  }

  private void spinUpWatchThread(BlockingQueue<TcpResponse> watchTcpResponseQueue,
      DiceDbCallBack callBack) {
    scheduler.execute(() -> {
      while (true) {
        try {
          TcpResponse tcpResponse = watchTcpResponseQueue.take();
          if (tcpResponse.isError) {
            logger.error("Error in watch thread: {}", String.valueOf(tcpResponse.exception));
            if (callBack != null) {
              callBack.call();
            }
            break;
          }
          if (tcpResponse.sessionEnded) {
            this.watchResponseQueue = null;
            if (callBack != null) {
              callBack.call();
            }
            logger.info("Session ended in watch thread");
            break;
          }
          byte[] response = tcpResponse.data;
          Response resp = Response.parseFrom(response);
          this.watchResponseQueue.put(resp);
        } catch (Exception e) {
          logger.error("Error in watch thread", e);
          break;
        }
      }
    });
  }

  @Override
  public boolean isHealthy() {
    return this.tcpClient != null && this.tcpClient.isHealthy();
  }

  private CommandProto.Command getHandShakeCommand(String mode) {
    if (this.clientId == null) {
      this.clientId = UUID.randomUUID().toString();
    }
    return CommandProto.Command.newBuilder()
        .setCmd("HANDSHAKE")
        .addArgs(clientId)
        .addArgs(mode)
        .build();
  }

  public String getClientId() {
    return clientId;
  }

  @Override
  public void close() {
    this.scheduler.shutdown();
    if (this.watchResponseQueue != null) {
      this.watchResponseQueue.clear();
    }
    if (this.tcpClient != null) {
      this.tcpClient.close();
    }
  }

}
