package com.nonfallable.taskKnight.security;

import com.nonfallable.taskKnight.exceptions.TraceKeeperException;

public class SecurityException extends TraceKeeperException {

    public SecurityException(String explanation) {
        super(explanation);
    }

    public SecurityException(String explanation, Throwable cause) {
        super(explanation, cause);
    }
}
