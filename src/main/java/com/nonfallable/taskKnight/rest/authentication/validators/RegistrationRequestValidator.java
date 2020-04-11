package com.nonfallable.taskKnight.rest.authentication.validators;

import com.nonfallable.taskKnight.rest.authentication.dto.RegistrationRequestDTO;
import com.nonfallable.taskKnight.utils.Validator;
import org.springframework.stereotype.Component;

@Component
public class RegistrationRequestValidator implements Validator<RegistrationRequestDTO> {

    @Override
    public void validate(RegistrationRequestDTO value) {

    }
}
