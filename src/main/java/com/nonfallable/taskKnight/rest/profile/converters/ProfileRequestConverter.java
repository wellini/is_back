package com.nonfallable.taskKnight.rest.profile.converters;

import com.nonfallable.taskKnight.models.Profile;
import com.nonfallable.taskKnight.rest.profile.dto.ProfileRequestDTO;
import com.nonfallable.taskKnight.utils.converters.ToDomainConverter;
import org.springframework.stereotype.Component;

@Component
public class ProfileRequestConverter extends ToDomainConverter<ProfileRequestDTO, Profile> {

    @Override
    public Profile toDomain(ProfileRequestDTO profileRequestDTO) {
        return new Profile()
                .setEmail(profileRequestDTO.getLogin())
                .setName(profileRequestDTO.getName());
    }
}
