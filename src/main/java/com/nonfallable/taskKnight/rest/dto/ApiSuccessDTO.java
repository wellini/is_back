package com.nonfallable.taskKnight.rest.dto;

public class ApiSuccessDTO {

    private String message;

    public String getMessage() {
        return message;
    }

    public ApiSuccessDTO setMessage(String message) {
        this.message = message;
        return this;
    }
}
