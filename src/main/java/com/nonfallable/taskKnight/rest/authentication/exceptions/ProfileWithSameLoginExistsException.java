package com.nonfallable.taskKnight.rest.authentication.exceptions;

import com.nonfallable.taskKnight.exceptions.TraceKeeperException;

public class ProfileWithSameLoginExistsException extends TraceKeeperException {

    public ProfileWithSameLoginExistsException() {
        super("Profile with same login exists");
    }
}
