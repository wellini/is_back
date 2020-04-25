package com.nonfallable.taskKnight.rest.profile.dto;

import java.util.UUID;

public class ProfileResponseDTO {

    private UUID id;

    private String login;

    private String name;

    public UUID getId() {
        return id;
    }

    public ProfileResponseDTO setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public ProfileResponseDTO setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProfileResponseDTO setName(String name) {
        this.name = name;
        return this;
    }
}
