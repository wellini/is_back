package com.nonfallable.taskKnight.rest.authentication.dto;

public class RegistrationRequestDTO {

    private String userName;
    private String login;
    private String password;

    public String getUserName() {
        return userName;
    }

    public RegistrationRequestDTO setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public RegistrationRequestDTO setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegistrationRequestDTO setPassword(String password) {
        this.password = password;
        return this;
    }
}
