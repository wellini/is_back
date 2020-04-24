package com.nonfallable.taskKnight.rest.dto;

public class ApiErrorDTO {

    private String message;

    public String getMessage() {
        return message;
    }

    public ApiErrorDTO setMessage(String message) {
        this.message = message;
        return this;
    }
}
