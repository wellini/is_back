package com.nonfallable.taskKnight.rest.authentication.validators;

import com.nonfallable.taskKnight.exceptions.ValidationException;
import com.nonfallable.taskKnight.rest.authentication.dto.ConfirmationCodeRequestDTO;
import com.nonfallable.taskKnight.utils.validation.Validator;
import com.nonfallable.taskKnight.utils.validation.implementations.ConfirmationCodeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConfirmationCodeRequestRequestValidator implements Validator<ConfirmationCodeRequestDTO> {

    @Autowired
    private ConfirmationCodeValidator confirmationCodeValidator;

    @Override
    public void validate(ConfirmationCodeRequestDTO value) throws ValidationException {
        confirmationCodeValidator.validate(value.getCode());
    }
}
