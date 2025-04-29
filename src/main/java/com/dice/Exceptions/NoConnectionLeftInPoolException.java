package com.dice.Exceptions;

public class NoConnectionLeftInPoolException extends Exception {
    public NoConnectionLeftInPoolException(String message) {
        super(message);
    }

    public NoConnectionLeftInPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoConnectionLeftInPoolException() {
        super("No connection left in the pool");
    }
}
