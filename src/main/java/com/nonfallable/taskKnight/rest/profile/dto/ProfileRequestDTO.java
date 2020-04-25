package com.nonfallable.taskKnight.rest.profile.dto;

public class ProfileRequestDTO {

    private String login;

    private String name;

    public String getLogin() {
        return login;
    }

    public ProfileRequestDTO setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProfileRequestDTO setName(String name) {
        this.name = name;
        return this;
    }
}
