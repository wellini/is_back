package com.nonfallable.taskKnight.security;

import com.nonfallable.taskKnight.models.Profile;
import com.nonfallable.taskKnight.repositories.ProfileRepository;
import com.nonfallable.taskKnight.security.converters.ProfileToUserDetailsConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileToUserDetailsConverter profileToUserDetailsConverter;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Profile profile = profileRepository
                .findByEmail(username)
                .orElseThrow(() -> new SecurityException("No user with provided username"));
        return profileToUserDetailsConverter.toUserDetails(profile);
    }
}
