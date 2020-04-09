package com.nonfallable.taskKnight.rest.authentication.dto;


import com.nonfallable.taskKnight.security.permissions.Permission;

import java.util.List;
import java.util.UUID;

public class AuthenticationResponseDTO {

    private UUID id;

    private String accessToken;

    private String expires;

    private String login;

    private List<Permission> permissions;

    public UUID getId() {
        return id;
    }

    public AuthenticationResponseDTO setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public AuthenticationResponseDTO setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public String getExpires() {
        return expires;
    }

    public AuthenticationResponseDTO setExpires(String expires) {
        this.expires = expires;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public AuthenticationResponseDTO setLogin(String login) {
        this.login = login;
        return this;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public AuthenticationResponseDTO setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
        return this;
    }
}
