package com.nonfallable.taskKnight.rest.authentication.validators;

import com.nonfallable.taskKnight.exceptions.ValidationException;
import com.nonfallable.taskKnight.rest.authentication.dto.AuthenticationRequestDTO;
import com.nonfallable.taskKnight.utils.validation.Validator;
import com.nonfallable.taskKnight.utils.validation.implementations.EmailValidator;
import com.nonfallable.taskKnight.utils.validation.implementations.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationRequestValidator implements Validator<AuthenticationRequestDTO> {

    @Autowired
    private EmailValidator emailValidator;

    @Autowired
    private PasswordValidator passwordValidator;

    @Override
    public void validate(AuthenticationRequestDTO value) throws ValidationException {
        emailValidator.validate(value.getLogin());
        passwordValidator.validate(value.getPassword());
    }
}
