package com.nonfallable.taskKnight.rest.tasks.validators;

import com.nonfallable.taskKnight.exceptions.ValidationException;
import com.nonfallable.taskKnight.rest.tasks.dto.TaskRequestDTO;
import com.nonfallable.taskKnight.utils.ValidationResultBuilder;
import com.nonfallable.taskKnight.utils.Validator;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SaveTaskRequestValidator implements Validator<TaskRequestDTO> {

    @Override
    public void validate(TaskRequestDTO value) throws ValidationException {
        shouldBeBetweenOneAndMinusOne(value.getImportance())
                .orElseThrow(() -> new ValidationException("Importance and urgency should belong [-1; 1]"));
        shouldBeBetweenOneAndMinusOne(value.getUrgency())
                .orElseThrow(() -> new ValidationException("Importance and urgency should belong [-1; 1]"));
        anyShouldBeNotEmpty(value.getText(), value.getTitle())
                .orElseThrow(() -> new ValidationException("Title or text should not be empty"));
    }

    private ValidationResultBuilder shouldBeBetweenOneAndMinusOne(float value) {
        return new ValidationResultBuilder(value >= -1 && value <= 1);
    }

    private ValidationResultBuilder anyShouldBeNotEmpty(String... strings) {
        return new ValidationResultBuilder(Arrays.stream(strings).anyMatch(str -> str != null && !str.isEmpty()));
    }
}
