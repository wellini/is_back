package com.nonfallable.taskKnight.rest.authentication.dto;

import com.nonfallable.taskKnight.security.permissions.Permission;

import java.util.List;

public class RegistrationResponseDTO {

    private String accessToken;
    private String confirmationToken;
    private String expires;
    private String login;
    private List<Permission> permissions;

    public String getAccessToken() {
        return accessToken;
    }

    public RegistrationResponseDTO setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public RegistrationResponseDTO setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
        return this;
    }

    public String getExpires() {
        return expires;
    }

    public RegistrationResponseDTO setExpires(String expires) {
        this.expires = expires;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public RegistrationResponseDTO setLogin(String login) {
        this.login = login;
        return this;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public RegistrationResponseDTO setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
        return this;
    }
}
