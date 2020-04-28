package com.nonfallable.taskKnight.utils.validation.implementations;

import com.nonfallable.taskKnight.exceptions.ValidationException;
import com.nonfallable.taskKnight.utils.validation.ValidationResultBuilder;
import com.nonfallable.taskKnight.utils.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class PasswordValidator implements Validator<String> {

    @Override
    public void validate(String value) throws ValidationException {
        String normalized = value.trim();
        shouldBeNotEmpty(normalized)
                .orElseThrow(() -> new ValidationException("Password shouldn't be empty"));
        shouldContainAtLeast8Symbols(normalized)
                .orElseThrow(() -> new ValidationException("Password should contain at least 8 symbols"));
        shouldContainLessOrEqual32Symbols(normalized)
                .orElseThrow(() -> new ValidationException("The greatest amount of symbols in password is 32"));
        shouldContainAZ09(normalized)
                .orElseThrow(() -> new ValidationException("Password should contain only latin symbols or digits"));
    }

    private ValidationResultBuilder shouldContainAZ09(String value) {
        return new ValidationResultBuilder(
                Pattern.compile("^[a-zA-Z0-9]*$")
                        .matcher(value)
                        .matches()
        );
    }

    private ValidationResultBuilder shouldBeNotEmpty(String value) {
        return new ValidationResultBuilder(value != null && !value.isEmpty());
    }

    private ValidationResultBuilder shouldContainAtLeast8Symbols(String value) {
        return new ValidationResultBuilder(value != null && value.length() >= 8);
    }

    private ValidationResultBuilder shouldContainLessOrEqual32Symbols(String value) {
        return new ValidationResultBuilder(value != null && value.length() <= 32);
    }
}
