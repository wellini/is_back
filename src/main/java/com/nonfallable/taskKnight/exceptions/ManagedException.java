package com.nonfallable.taskKnight.exceptions;

public class ManagedException extends RuntimeException {
    private String explanation;

    public String getExplanation() {
        return explanation;
    }

    public ManagedException(String explanation) {
        this.explanation = explanation;
    }

    public ManagedException(String explanation, String message) {
        super(message);
        this.explanation = explanation;
    }

    public ManagedException(String explanation, String message, Throwable cause) {
        super(message, cause);
        this.explanation = explanation;
    }

    public ManagedException(String explanation, Throwable cause) {
        super(cause);
        this.explanation = explanation;
    }

    public ManagedException(String explanation, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.explanation = explanation;
    }
}
