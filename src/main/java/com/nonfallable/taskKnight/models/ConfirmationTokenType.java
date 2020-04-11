package com.nonfallable.taskKnight.models;

import java.time.Duration;

public enum ConfirmationTokenType {
    REGISTRATION(Duration.ofDays(365)),
    CHANGE_PASSWORD(Duration.ofHours(2));

    private Duration duration;

    ConfirmationTokenType(Duration duration) {
        this.duration = duration;
    }

    public Duration getDuration() {
        return duration;
    }
}
