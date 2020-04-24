package com.nonfallable.taskKnight.rest.tasks.dto;

import com.nonfallable.taskKnight.models.Profile;

import java.time.ZonedDateTime;
import java.util.UUID;

public class TaskResponseDTO {

    private UUID id;

    private String title;

    private String text;

    private float importance;

    private float urgency;

    private Profile author;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    public TaskResponseDTO() {
    }

    public TaskResponseDTO(UUID id, String title, String text, float importance, float urgency, Profile author, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.importance = importance;
        this.urgency = urgency;
        this.author = author;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public TaskResponseDTO setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public TaskResponseDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getText() {
        return text;
    }

    public TaskResponseDTO setText(String text) {
        this.text = text;
        return this;
    }

    public float getImportance() {
        return importance;
    }

    public TaskResponseDTO setImportance(float importance) {
        this.importance = importance;
        return this;
    }

    public float getUrgency() {
        return urgency;
    }

    public TaskResponseDTO setUrgency(float urgency) {
        this.urgency = urgency;
        return this;
    }

    public Profile getAuthor() {
        return author;
    }

    public TaskResponseDTO setAuthor(Profile author) {
        this.author = author;
        return this;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public TaskResponseDTO setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public TaskResponseDTO setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
