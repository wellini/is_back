package com.nonfallable.taskKnight.services;

import com.nonfallable.taskKnight.models.Profile;
import com.nonfallable.taskKnight.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Transactional(propagation = Propagation.MANDATORY)
    public Profile getAndLockAuthorizedUserProfile() {
        return profileRepository.findAndLockByEmail(getUserDetails().getUsername()).get();
    }

    public Profile getAuthorizedUserProfile() {
        return profileRepository.findByEmail(getUserDetails().getUsername()).get();
    }

    private UserDetails getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetails) authentication.getPrincipal();
    }
}
