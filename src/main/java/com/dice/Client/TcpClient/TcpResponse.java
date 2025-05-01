package com.dice.Client.TcpClient;

import com.dice.Exceptions.DiceDbTcpException;

public class TcpResponse {
    public final byte[] data;
    public final boolean isError;
    public final DiceDbTcpException exception;
    public final boolean sessionEnded;

    public TcpResponse(byte[] data) {
        this.data = data;
        this.isError = false;
        this.exception = null;
        this.sessionEnded = false;
    }

    public TcpResponse(Throwable throwable) {
        this.data = null;
        this.isError = true;
        this.exception = new DiceDbTcpException(throwable);
        this.sessionEnded = false;
    }

    public TcpResponse() {
        this.data = null;
        this.isError = false;
        this.exception = null;
        this.sessionEnded = true;
    }
}
