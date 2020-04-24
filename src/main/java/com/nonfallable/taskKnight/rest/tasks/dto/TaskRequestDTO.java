package com.nonfallable.taskKnight.rest.tasks.dto;

public class TaskRequestDTO {

    private String title;

    private String text;

    private float importance;

    private float urgency;

    public String getTitle() {
        return title;
    }

    public TaskRequestDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getText() {
        return text;
    }

    public TaskRequestDTO setText(String text) {
        this.text = text;
        return this;
    }

    public float getImportance() {
        return importance;
    }

    public TaskRequestDTO setImportance(float importance) {
        this.importance = importance;
        return this;
    }

    public float getUrgency() {
        return urgency;
    }

    public TaskRequestDTO setUrgency(float urgency) {
        this.urgency = urgency;
        return this;
    }
}
