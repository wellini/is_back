package com.nonfallable.taskKnight.services;

import com.nonfallable.taskKnight.models.ConfirmationToken;
import com.nonfallable.taskKnight.models.Profile;
import com.nonfallable.taskKnight.repositories.ConfirmationTokenRepository;
import com.nonfallable.taskKnight.repositories.ProfileRepository;
import com.nonfallable.taskKnight.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private TaskRepository taskRepository;

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

    @Transactional
    public void completeDeletion(Profile profile) {
        List<ConfirmationToken> allTokens = confirmationTokenRepository.findAndLockAllBySubject(profile.getEmail());
        confirmationTokenRepository.deleteAll(allTokens);
        taskRepository.deleteAllByAuthor(profile);
        profileRepository.delete(profile);
    }
}
