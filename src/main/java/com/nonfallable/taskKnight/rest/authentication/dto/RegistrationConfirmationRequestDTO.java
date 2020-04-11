package com.nonfallable.taskKnight.rest.authentication.dto;

public class RegistrationConfirmationRequestDTO {

    private String code;

    public String getCode() {
        return code;
    }

    public RegistrationConfirmationRequestDTO setCode(String code) {
        this.code = code;
        return this;
    }
}
