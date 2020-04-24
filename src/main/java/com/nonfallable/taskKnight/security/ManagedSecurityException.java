package com.nonfallable.taskKnight.security;

import com.nonfallable.taskKnight.exceptions.ManagedException;

public class ManagedSecurityException extends ManagedException {

    public ManagedSecurityException(String explanation) {
        super(explanation);
    }

    public ManagedSecurityException(String explanation, Throwable cause) {
        super(explanation, cause);
    }
}
