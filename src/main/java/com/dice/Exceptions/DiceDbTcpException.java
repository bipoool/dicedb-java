package com.dice.Exceptions;

public class DiceDbTcpException extends Exception {
    public DiceDbTcpException(String message) {
        super(message);
    }

    public DiceDbTcpException(Throwable cause) {
        super(cause);
    }

    public DiceDbTcpException() {
        super("Error in response from server");
    }

    public DiceDbTcpException(Exception e) {
        super(e);
    }
}
