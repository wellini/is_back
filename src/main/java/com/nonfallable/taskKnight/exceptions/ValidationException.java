package com.nonfallable.taskKnight.exceptions;

public class ValidationException extends ManagedException {

    public ValidationException(String explanation) {
        super(explanation);
    }

    public ValidationException(String explanation, Throwable cause) {
        super(explanation, cause);
    }
}
