package com.nonfallable.taskKnight.security.converters;

import com.nonfallable.taskKnight.models.Profile;
import com.nonfallable.taskKnight.security.UserDetailsImpl;
import com.nonfallable.taskKnight.security.permissions.PermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class ProfileToUserDetailsConverter {

    @Autowired
    private PermissionsService permissionsService;

    public UserDetails toUserDetails(Profile profile) {
        UserDetailsImpl userDetails = new UserDetailsImpl()
                .setId(profile.getId())
                .setPassword(profile.getPassword())
                .setRole(profile.getRole())
                .setUsername(profile.getEmail())
                .setAuthorities(permissionsService.getPermissionsByRole(profile.getRole()));
        return userDetails;
    }
}
