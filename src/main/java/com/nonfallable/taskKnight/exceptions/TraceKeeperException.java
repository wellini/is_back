package com.nonfallable.taskKnight.exceptions;

public class TraceKeeperException extends RuntimeException {
    private String explanation;

    public String getExplanation() {
        return explanation;
    }

    public TraceKeeperException(String explanation) {
        this.explanation = explanation;
    }

    public TraceKeeperException(String explanation, String message) {
        super(message);
        this.explanation = explanation;
    }

    public TraceKeeperException(String explanation, String message, Throwable cause) {
        super(message, cause);
        this.explanation = explanation;
    }

    public TraceKeeperException(String explanation, Throwable cause) {
        super(cause);
        this.explanation = explanation;
    }

    public TraceKeeperException(String explanation, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.explanation = explanation;
    }
}
