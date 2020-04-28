package com.nonfallable.taskKnight.utils.validation;

import com.nonfallable.taskKnight.exceptions.ValidationException;

import java.util.function.Supplier;

public class ValidationResultBuilder {
    private final boolean valid;

    public ValidationResultBuilder(boolean valid) {
        this.valid = valid;
    }

    public void orElseThrow(Supplier<? extends ValidationException> supplier) throws ValidationException {
        if(!valid) throw supplier.get();
    }
}
