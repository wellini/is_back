package com.nonfallable.taskKnight.rest.authentication.exceptions;

import com.nonfallable.taskKnight.security.SecurityException;

public class BadCredentialsSecurityException extends SecurityException {

    public BadCredentialsSecurityException(String explanation, Throwable cause) {
        super(explanation, cause);
    }
}
