package com.nonfallable.taskKnight.rest.authentication.dto;

public class ConfirmationCodeResponseDTO {

    private String message;

    public ConfirmationCodeResponseDTO() {
    }

    public ConfirmationCodeResponseDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public ConfirmationCodeResponseDTO setMessage(String message) {
        this.message = message;
        return this;
    }
}
