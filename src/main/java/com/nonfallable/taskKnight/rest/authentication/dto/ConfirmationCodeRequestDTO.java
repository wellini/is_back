package com.nonfallable.taskKnight.rest.authentication.dto;

public class ConfirmationCodeRequestDTO {

    private String code;

    public String getCode() {
        return code;
    }

    public ConfirmationCodeRequestDTO setCode(String code) {
        this.code = code;
        return this;
    }
}
