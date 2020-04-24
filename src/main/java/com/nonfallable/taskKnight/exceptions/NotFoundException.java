package com.nonfallable.taskKnight.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends ManagedException {

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
