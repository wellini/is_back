package com.nonfallable.taskKnight.rest.profile.validators;

import com.nonfallable.taskKnight.exceptions.ValidationException;
import com.nonfallable.taskKnight.rest.profile.dto.ProfileRequestDTO;
import com.nonfallable.taskKnight.utils.Validator;
import org.springframework.stereotype.Component;

@Component
public class SaveProfileRequestValidator implements Validator<ProfileRequestDTO> {

    @Override
    public void validate(ProfileRequestDTO value) throws ValidationException {

    }
}
