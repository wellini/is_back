package com.nonfallable.taskKnight.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String title;

    private String text;

    private float importance;

    private float urgency;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile author;

    @Column(name = "created_at")
    private ZonedDateTime createdAt = ZonedDateTime.now();

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt = ZonedDateTime.now();

    public UUID getId() {
        return id;
    }

    public Task setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Task setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getText() {
        return text;
    }

    public Task setText(String text) {
        this.text = text;
        return this;
    }

    public float getImportance() {
        return importance;
    }

    public Task setImportance(float importance) {
        this.importance = importance;
        return this;
    }

    public float getUrgency() {
        return urgency;
    }

    public Task setUrgency(float urgency) {
        this.urgency = urgency;
        return this;
    }

    public Profile getAuthor() {
        return author;
    }

    public Task setAuthor(Profile author) {
        this.author = author;
        return this;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public Task setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Task setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
