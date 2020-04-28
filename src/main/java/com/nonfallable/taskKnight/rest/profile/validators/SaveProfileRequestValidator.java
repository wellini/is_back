package com.nonfallable.taskKnight.rest.profile.validators;

import com.nonfallable.taskKnight.exceptions.ValidationException;
import com.nonfallable.taskKnight.rest.profile.dto.ProfileRequestDTO;
import com.nonfallable.taskKnight.utils.validation.Validator;
import com.nonfallable.taskKnight.utils.validation.implementations.EmailValidator;
import com.nonfallable.taskKnight.utils.validation.implementations.UserNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveProfileRequestValidator implements Validator<ProfileRequestDTO> {

    @Autowired
    private UserNameValidator userNameValidator;

    @Autowired
    private EmailValidator emailValidator;

    @Override
    public void validate(ProfileRequestDTO value) throws ValidationException {
        userNameValidator.validate(value.getName());
        emailValidator.validate(value.getLogin());
    }
}
