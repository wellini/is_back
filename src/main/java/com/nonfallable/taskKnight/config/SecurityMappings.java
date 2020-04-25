package com.nonfallable.taskKnight.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;


public class SecurityMappings extends AbstractHttpConfigurer<SecurityMappings, HttpSecurity> {

    @Override
    public void init(HttpSecurity builder) throws Exception {
        builder.authorizeRequests()
                // Authentication & authorization
                .antMatchers("/1.0/access-denied").permitAll()
                .antMatchers("/1.0/login").permitAll()
                .antMatchers("/1.0/registration").permitAll()
                .antMatchers("/1.0/registration/confirm/**").authenticated()
                .antMatchers("/1.0/logout").permitAll()
                .antMatchers("/1.0./change-password").hasAuthority("UPDATE_OWN_PASSWORD")
                .antMatchers("/1.0/change-password/caonfirm/**").hasAuthority("UPDATE_OWN_PASSWORD")
                .antMatchers("/1.0/auth").hasAuthority("READ_AUTHENTICATION")
                // Tasks
                .antMatchers("/1.0/listTasks").hasAuthority("READ_OWN_TASKS")
                .antMatchers("/1.0/getTask/**").hasAuthority("READ_OWN_TASKS")
                .antMatchers("/1.0/createTask").hasAuthority("CREATE_OWN_TASKS")
                .antMatchers("/1.0/updateTask/**").hasAuthority("UPDATE_OWN_TASKS")
                .antMatchers("/1.0/deleteTask/**").hasAuthority("DELETE_OWN_TASKS")
                // Profiles
                .antMatchers("/1.0/listProfiles").hasAuthority("READ_ALL_PROFILES")
                .antMatchers("/1.0/getProfile/**").hasAuthority("READ_ALL_PROFILES")
                .antMatchers("/1.0/iam").hasAuthority("READ_OWN_PROFILE")
                .antMatchers("/1.0/updateMyProfile").hasAuthority("UPDATE_OWN_PROFILE")
                .antMatchers("/1.0/updateProfile/**").hasAuthority("UPDATE_ALL_PROFILES")
                .antMatchers("/1.0/deleteMyProfile").hasAuthority("DELETE_OWN_PROFILE")
                .antMatchers("/1.0/deleteProfile/**").hasAuthority("DELETE_ALL_PROFILES")
                .anyRequest().authenticated();
    }

    public static SecurityMappings securityMappings() {
        return new SecurityMappings();
    }
}
