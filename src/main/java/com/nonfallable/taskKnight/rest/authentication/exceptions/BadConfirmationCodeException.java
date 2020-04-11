package com.nonfallable.taskKnight.rest.authentication.exceptions;

import com.nonfallable.taskKnight.exceptions.TraceKeeperException;

public class BadConfirmationCodeException extends TraceKeeperException {

    public BadConfirmationCodeException() {
        super("Wrong confirmation code");
    }
}
