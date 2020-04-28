package com.nonfallable.taskKnight.utils.validation.implementations;

import com.nonfallable.taskKnight.exceptions.ValidationException;
import com.nonfallable.taskKnight.utils.validation.ValidationResultBuilder;
import com.nonfallable.taskKnight.utils.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class EmailValidator implements Validator<String> {

    @Override
    public void validate(String value) throws ValidationException {
        String normalized = value.trim();
        shouldBeNotEmpty(normalized)
                .orElseThrow(() -> new ValidationException("Email shouldn't be empty"));
        shouldNotContainSpecialSymbols(normalized)
                .orElseThrow(() -> new ValidationException("Email should match usual format, for example yourmail@email.com"));
    }

    private ValidationResultBuilder shouldBeNotEmpty(String value) {
        return new ValidationResultBuilder(value != null && !value.isEmpty());
    }

    private ValidationResultBuilder shouldNotContainSpecialSymbols(String value) {
        return new ValidationResultBuilder(
                Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$")
                        .matcher(value)
                        .matches()
        );
    }

    private ValidationResultBuilder shouldContainAtLeast8Symbols(String value) {
        return new ValidationResultBuilder(value != null && value.length() >= 8);
    }

    private ValidationResultBuilder shouldContainLessOrEqual64Symbols(String value) {
        return new ValidationResultBuilder(value != null && value.length() <= 64);
    }
}
