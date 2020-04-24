package com.nonfallable.taskKnight.rest.authentication.exceptions;

import com.nonfallable.taskKnight.exceptions.ManagedException;

public class ConfirmationTokenNotFoundException extends ManagedException {

    public ConfirmationTokenNotFoundException(String explanation) {
        super(explanation);
    }
}
