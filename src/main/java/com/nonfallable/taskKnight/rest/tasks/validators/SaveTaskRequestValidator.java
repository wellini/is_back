package com.nonfallable.taskKnight.rest.tasks.validators;

import com.nonfallable.taskKnight.exceptions.ValidationException;
import com.nonfallable.taskKnight.rest.tasks.dto.TaskRequestDTO;
import com.nonfallable.taskKnight.utils.validation.ValidationResultBuilder;
import com.nonfallable.taskKnight.utils.validation.Validator;
import com.nonfallable.taskKnight.utils.validation.implementations.ImportanceValidator;
import com.nonfallable.taskKnight.utils.validation.implementations.UrgencyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SaveTaskRequestValidator implements Validator<TaskRequestDTO> {

    @Autowired
    private ImportanceValidator importanceValidator;

    @Autowired
    private UrgencyValidator urgencyValidator;

    @Override
    public void validate(TaskRequestDTO value) throws ValidationException {
        importanceValidator.validate(value.getImportance());
        urgencyValidator.validate(value.getUrgency());

        String normalizedText = value.getText().trim();
        String normalizedTitle = value.getTitle().trim();
        anyShouldBeNotEmpty(normalizedText, normalizedTitle)
                .orElseThrow(() -> new ValidationException("Title or text should not be empty"));
    }

    private ValidationResultBuilder anyShouldBeNotEmpty(String... strings) {
        return new ValidationResultBuilder(Arrays.stream(strings).anyMatch(str -> str != null && !str.isEmpty()));
    }
}
