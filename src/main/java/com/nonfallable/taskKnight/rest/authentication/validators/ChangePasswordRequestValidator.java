package com.nonfallable.taskKnight.rest.authentication.validators;

import com.nonfallable.taskKnight.exceptions.ValidationException;
import com.nonfallable.taskKnight.rest.authentication.dto.ChangePasswordRequestDTO;
import com.nonfallable.taskKnight.utils.validation.Validator;
import com.nonfallable.taskKnight.utils.validation.implementations.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChangePasswordRequestValidator implements Validator<ChangePasswordRequestDTO> {

    @Autowired
    private PasswordValidator passwordValidator;

    @Override
    public void validate(ChangePasswordRequestDTO value) throws ValidationException {
        passwordValidator.validate(value.getNewPassword());
    }
}
