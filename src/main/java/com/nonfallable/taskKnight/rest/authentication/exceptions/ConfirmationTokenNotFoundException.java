package com.nonfallable.taskKnight.rest.authentication.exceptions;

import com.nonfallable.taskKnight.exceptions.TraceKeeperException;

public class ConfirmationTokenNotFoundException extends TraceKeeperException {

    public ConfirmationTokenNotFoundException(String explanation) {
        super(explanation);
    }
}
