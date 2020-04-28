package com.nonfallable.taskKnight.utils.validation.implementations;

import com.nonfallable.taskKnight.exceptions.ValidationException;
import com.nonfallable.taskKnight.utils.validation.ValidationResultBuilder;
import com.nonfallable.taskKnight.utils.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class UserNameValidator implements Validator<String> {

    @Override
    public void validate(String value) throws ValidationException {
        String normalized = value.trim();
        shouldBeNotEmpty(normalized)
                .orElseThrow(() -> new ValidationException("User's name shouldn't be empty"));
        shouldContainAtLeast8Symbols(normalized)
                .orElseThrow(() -> new ValidationException("User's name should contain at least 8 symbols"));
        shouldContainLessOrEqual64Symbols(normalized)
                .orElseThrow(() -> new ValidationException("The greatest amount of symbols in user's name is 64"));
        shouldNotContainSpecialSymbols(normalized)
                .orElseThrow(() -> new ValidationException("User's name could contain latin symbols, digits, '_', '-', '.', or spaces"));
    }

    private ValidationResultBuilder shouldBeNotEmpty(String value) {
        return new ValidationResultBuilder(value != null && !value.isEmpty());
    }

    private ValidationResultBuilder shouldContainAtLeast8Symbols(String value) {
        return new ValidationResultBuilder(value != null && value.length() >= 8);
    }

    private ValidationResultBuilder shouldContainLessOrEqual64Symbols(String value) {
        return new ValidationResultBuilder(value != null && value.length() <= 64);
    }

    private ValidationResultBuilder shouldNotContainSpecialSymbols(String value) {
        return new ValidationResultBuilder(
                Pattern.compile("^[\\sA-Za-z0-9_.-]+$")
                        .matcher(value)
                        .matches()
        );
    }
}
