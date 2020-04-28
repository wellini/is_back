package com.nonfallable.taskKnight.utils.validation.implementations;

import com.nonfallable.taskKnight.exceptions.ValidationException;
import com.nonfallable.taskKnight.utils.validation.ValidationResultBuilder;
import com.nonfallable.taskKnight.utils.validation.Validator;
import org.springframework.stereotype.Component;

@Component
public class UrgencyValidator implements Validator<Float> {

    @Override
    public void validate(Float value) throws ValidationException {
        shouldBeNotEmpty(value)
                .orElseThrow(() -> new ValidationException("Urgency should exist"));
        shouldBelongUrgencyInterval(value)
                .orElseThrow(() -> new ValidationException("Urgency should belong [-1; 1]"));
    }

    private ValidationResultBuilder shouldBeNotEmpty(Float value) {
        return new ValidationResultBuilder(value != null);
    }

    private ValidationResultBuilder shouldBelongUrgencyInterval(Float value) {
        return new ValidationResultBuilder(value <= 1 && value >= -1);
    }
}
