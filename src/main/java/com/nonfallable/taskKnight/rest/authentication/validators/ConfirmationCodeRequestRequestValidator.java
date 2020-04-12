package com.nonfallable.taskKnight.rest.authentication.validators;

import com.nonfallable.taskKnight.exceptions.ValidationException;
import com.nonfallable.taskKnight.rest.authentication.dto.ConfirmationCodeRequestDTO;
import com.nonfallable.taskKnight.utils.Validator;
import org.springframework.stereotype.Component;

@Component
public class ConfirmationCodeRequestRequestValidator implements Validator<ConfirmationCodeRequestDTO> {

    @Override
    public void validate(ConfirmationCodeRequestDTO value) throws ValidationException {

    }
}
