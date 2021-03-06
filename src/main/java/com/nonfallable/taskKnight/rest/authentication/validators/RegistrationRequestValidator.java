package com.nonfallable.taskKnight.rest.authentication.validators;

import com.nonfallable.taskKnight.rest.authentication.dto.RegistrationRequestDTO;
import com.nonfallable.taskKnight.utils.validation.Validator;
import com.nonfallable.taskKnight.utils.validation.implementations.EmailValidator;
import com.nonfallable.taskKnight.utils.validation.implementations.PasswordValidator;
import com.nonfallable.taskKnight.utils.validation.implementations.UserNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrationRequestValidator implements Validator<RegistrationRequestDTO> {

    @Autowired
    private UserNameValidator userNameValidator;

    @Autowired
    private EmailValidator emailValidator;

    @Autowired
    private PasswordValidator passwordValidator;

    @Override
    public void validate(RegistrationRequestDTO value) {
        userNameValidator.validate(value.getUserName());
        emailValidator.validate(value.getLogin());
        passwordValidator.validate(value.getPassword());
    }
}
