package com.nonfallable.taskKnight.utils.validation.implementations;

import com.nonfallable.taskKnight.exceptions.ValidationException;
import com.nonfallable.taskKnight.utils.validation.ValidationResultBuilder;
import com.nonfallable.taskKnight.utils.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ConfirmationCodeValidator implements Validator<String> {

    @Override
    public void validate(String value) throws ValidationException {
        String normalized = value.trim();
        shouldBeNotEmpty(normalized)
                .orElseThrow(() -> new ValidationException("Code shouldn't be empty"));
        shouldContain6Symbols(normalized)
                .orElseThrow(() -> new ValidationException("Code should contain 6 symbols"));
        shouldNotContainSpecialSymbols(normalized)
                .orElseThrow(() -> new ValidationException("Code should contain only digits"));
    }

    private ValidationResultBuilder shouldBeNotEmpty(String value) {
        return new ValidationResultBuilder(value != null && !value.isEmpty());
    }

    private ValidationResultBuilder shouldContain6Symbols(String value) {
        return new ValidationResultBuilder(value != null && value.length() == 6);
    }

    private ValidationResultBuilder shouldNotContainSpecialSymbols(String value) {
        return new ValidationResultBuilder(
                Pattern.compile("^[0-9]+$")
                        .matcher(value)
                        .matches()
        );
    }
}
