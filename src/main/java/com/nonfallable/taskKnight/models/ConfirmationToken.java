package com.nonfallable.taskKnight.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "confirmation_token")
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String code;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    private String subject;

    @Enumerated(EnumType.STRING)
    private ConfirmationTokenType type;

    public String getSubject() {
        return subject;
    }

    public ConfirmationToken setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public ConfirmationToken setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public ConfirmationToken setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ConfirmationToken setCode(String code) {
        this.code = code;
        return this;
    }

    public ConfirmationTokenType getType() {
        return type;
    }

    public ConfirmationToken setType(ConfirmationTokenType type) {
        this.type = type;
        return this;
    }
}
