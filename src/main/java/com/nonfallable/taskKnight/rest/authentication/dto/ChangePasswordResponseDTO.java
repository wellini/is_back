package com.nonfallable.taskKnight.rest.authentication.dto;

import java.util.UUID;

public class ChangePasswordResponseDTO {

    private UUID confirmationToken;

    public ChangePasswordResponseDTO() {
    }

    public ChangePasswordResponseDTO(UUID confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public UUID getConfirmationToken() {
        return confirmationToken;
    }

    public ChangePasswordResponseDTO setConfirmationToken(UUID confirmationToken) {
        this.confirmationToken = confirmationToken;
        return this;
    }
}
