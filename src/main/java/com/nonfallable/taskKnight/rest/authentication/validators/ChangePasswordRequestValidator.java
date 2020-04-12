package com.nonfallable.taskKnight.rest.authentication.validators;

import com.nonfallable.taskKnight.exceptions.ValidationException;
import com.nonfallable.taskKnight.rest.authentication.dto.ChangePasswordRequestDTO;
import com.nonfallable.taskKnight.utils.Validator;
import org.springframework.stereotype.Component;

@Component
public class ChangePasswordRequestValidator implements Validator<ChangePasswordRequestDTO> {

    @Override
    public void validate(ChangePasswordRequestDTO value) throws ValidationException {

    }
}
