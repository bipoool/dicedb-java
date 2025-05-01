package com.dice.Exceptions;

public class DiceDbException extends Exception {
    public DiceDbException(String message) {
        super(message);
    }

    public DiceDbException(String message, Throwable cause) {
        super(message, cause);
    }

    public DiceDbException(Throwable cause) {
        super(cause);
    }

    public DiceDbException() {
        super("An error occurred in the DiceDB");
    }

    public DiceDbException(Exception e) {
        super(e);
    }
}
