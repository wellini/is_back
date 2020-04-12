package com.nonfallable.taskKnight.rest.authentication.dto;

public class ChangePasswordRequestDTO {

    private String newPassword;

    public ChangePasswordRequestDTO() {
    }

    public String getNewPassword() {
        return newPassword;
    }

    public ChangePasswordRequestDTO setNewPassword(String newPassword) {
        this.newPassword = newPassword;
        return this;
    }
}
