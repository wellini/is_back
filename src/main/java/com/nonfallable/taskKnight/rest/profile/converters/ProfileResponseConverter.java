package com.nonfallable.taskKnight.rest.profile.converters;

import com.nonfallable.taskKnight.models.Profile;
import com.nonfallable.taskKnight.rest.profile.dto.ProfileResponseDTO;
import com.nonfallable.taskKnight.utils.converters.FromDomainConverter;
import org.springframework.stereotype.Component;

@Component
public class ProfileResponseConverter extends FromDomainConverter<ProfileResponseDTO, Profile> {

    @Override
    public ProfileResponseDTO fromDomain(Profile profile) {
        return new ProfileResponseDTO()
                .setId(profile.getId())
                .setLogin(profile.getEmail())
                .setName(profile.getName());
    }
}
