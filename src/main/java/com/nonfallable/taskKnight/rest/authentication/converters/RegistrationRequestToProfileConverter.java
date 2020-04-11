package com.nonfallable.taskKnight.rest.authentication.converters;

import com.nonfallable.taskKnight.models.Profile;
import com.nonfallable.taskKnight.rest.authentication.dto.RegistrationRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RegistrationRequestToProfileConverter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Profile toDomain(RegistrationRequestDTO requestDTO) {
        return new Profile()
                .setName(requestDTO.getUserName())
                .setEmail(requestDTO.getLogin())
                .setPassword(passwordEncoder.encode(requestDTO.getPassword()));
    }
}
