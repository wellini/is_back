package com.nonfallable.taskKnight.rest.authentication.exceptions;

import com.nonfallable.taskKnight.exceptions.ManagedException;

public class ProfileWithSameLoginExistsException extends ManagedException {

    public ProfileWithSameLoginExistsException() {
        super("Profile with same login exists");
    }
}
