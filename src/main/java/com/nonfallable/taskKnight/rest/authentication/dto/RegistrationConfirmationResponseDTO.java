package com.nonfallable.taskKnight.rest.authentication.dto;

public class RegistrationConfirmationResponseDTO {

    private String message;

    public String getMessage() {
        return message;
    }

    public RegistrationConfirmationResponseDTO setMessage(String message) {
        this.message = message;
        return this;
    }
}
