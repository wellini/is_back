package com.nonfallable.taskKnight.utils.validation.implementations;

import com.nonfallable.taskKnight.exceptions.ValidationException;
import com.nonfallable.taskKnight.utils.validation.ValidationResultBuilder;
import com.nonfallable.taskKnight.utils.validation.Validator;
import org.springframework.stereotype.Component;

@Component
public class ImportanceValidator implements Validator<Float> {

    @Override
    public void validate(Float value) throws ValidationException {
        shouldBeNotEmpty(value)
                .orElseThrow(() -> new ValidationException("Importance should exist"));
        shouldBelongImportanceInterval(value)
                .orElseThrow(() -> new ValidationException("Importance should belong [-1; 1]"));
    }

    private ValidationResultBuilder shouldBeNotEmpty(Float value) {
        return new ValidationResultBuilder(value != null);
    }

    private ValidationResultBuilder shouldBelongImportanceInterval(Float value) {
        return new ValidationResultBuilder(value <= 1 && value >= -1);
    }
}
