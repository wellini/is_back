package com.nonfallable.taskKnight.rest.authentication.validators;

import com.nonfallable.taskKnight.exceptions.ValidationException;
import com.nonfallable.taskKnight.rest.authentication.dto.RegistrationConfirmationRequestDTO;
import com.nonfallable.taskKnight.utils.Validator;
import org.springframework.stereotype.Component;

@Component
public class RegistrationConfirmationRequestValidator implements Validator<RegistrationConfirmationRequestDTO> {

    @Override
    public void validate(RegistrationConfirmationRequestDTO value) throws ValidationException {

    }
}
