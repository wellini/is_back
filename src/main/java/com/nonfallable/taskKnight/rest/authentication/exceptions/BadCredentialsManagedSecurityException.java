package com.nonfallable.taskKnight.rest.authentication.exceptions;

import com.nonfallable.taskKnight.security.ManagedSecurityException;

public class BadCredentialsManagedSecurityException extends ManagedSecurityException {

    private final static String EXPLANATION = "Login or password are incorrect";

    public BadCredentialsManagedSecurityException(Throwable cause) {
        super(EXPLANATION, cause);
    }

    public BadCredentialsManagedSecurityException() {
        super(EXPLANATION);
    }
}
