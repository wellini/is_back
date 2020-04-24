package com.nonfallable.taskKnight.rest.authentication.exceptions;

import com.nonfallable.taskKnight.exceptions.ManagedException;

public class BadConfirmationCodeException extends ManagedException {

    public BadConfirmationCodeException() {
        super("Wrong confirmation code");
    }
}
