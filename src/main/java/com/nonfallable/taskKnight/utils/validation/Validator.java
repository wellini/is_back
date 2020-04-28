package com.nonfallable.taskKnight.utils.validation;

import com.nonfallable.taskKnight.exceptions.ValidationException;

public interface Validator<T> {
    void validate(T value) throws ValidationException;
}
