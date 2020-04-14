package com.nonfallable.taskKnight.exceptions;

public class NotFoundException extends TraceKeeperException {

    public NotFoundException(String explanation) {
        super(explanation);
    }

    public NotFoundException(String explanation, String message) {
        super(explanation, message);
    }

    public NotFoundException(String explanation, String message, Throwable cause) {
        super(explanation, message, cause);
    }

    public NotFoundException(String explanation, Throwable cause) {
        super(explanation, cause);
    }

    public NotFoundException(String explanation, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(explanation, message, cause, enableSuppression, writableStackTrace);
    }
}
