package com.nonfallable.taskKnight.security.converters;

import com.nonfallable.taskKnight.models.Profile;
import com.nonfallable.taskKnight.security.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class ProfileToUserDetailsConverter {

    public UserDetails toUserDetails(Profile profile) {
        UserDetailsImpl userDetails = new UserDetailsImpl()
                .setId(profile.getId())
                .setPassword(profile.getPassword())
                .setRole(profile.getRole())
                .setUsername(profile.getEmail());
        return userDetails;
    }
}
