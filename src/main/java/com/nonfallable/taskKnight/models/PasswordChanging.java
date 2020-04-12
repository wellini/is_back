package com.nonfallable.taskKnight.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "password_changing")
public class PasswordChanging {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Profile subject;

    @Column(name = "new_password")
    private String newPassword;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public PasswordChanging setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public PasswordChanging setId(UUID id) {
        this.id = id;
        return this;
    }

    public Profile getSubject() {
        return subject;
    }

    public PasswordChanging setSubject(Profile subject) {
        this.subject = subject;
        return this;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public PasswordChanging setNewPassword(String newPassword) {
        this.newPassword = newPassword;
        return this;
    }
}
