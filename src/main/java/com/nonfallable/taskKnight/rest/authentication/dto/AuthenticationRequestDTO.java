package com.nonfallable.taskKnight.rest.authentication.dto;

public class AuthenticationRequestDTO {

    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public AuthenticationRequestDTO setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AuthenticationRequestDTO setPassword(String password) {
        this.password = password;
        return this;
    }
}
