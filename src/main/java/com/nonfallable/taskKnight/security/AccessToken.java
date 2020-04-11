package com.nonfallable.taskKnight.security;

import java.time.LocalDateTime;

public class AccessToken {

    private String token;
    private LocalDateTime expiredAt;
    private String subject;

    public AccessToken() {
    }

    public String getSubject() {
        return subject;
    }

    public AccessToken setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public AccessToken(String token, LocalDateTime expiredAt) {
        this.token = token;
        this.expiredAt = expiredAt;
    }

    public String getToken() {
        return token;
    }

    public AccessToken setToken(String token) {
        this.token = token;
        return this;
    }

    public LocalDateTime getExpiredAt() {
        return expiredAt;
    }

    public AccessToken setExpiredAt(LocalDateTime expiredAt) {
        this.expiredAt = expiredAt;
        return this;
    }
}
