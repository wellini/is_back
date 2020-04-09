package com.nonfallable.taskKnight.security.permissions;

public class Permission {

    private String id;
    private String description;

    public String getId() {
        return id;
    }

    public Permission setId(String id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Permission setDescription(String description) {
        this.description = description;
        return this;
    }
}
