package com.nonfallable.taskKnight.rest.authentication;

import com.nonfallable.taskKnight.exceptions.NotFoundException;
import com.nonfallable.taskKnight.models.PasswordChanging;
import com.nonfallable.taskKnight.models.Profile;
import com.nonfallable.taskKnight.repositories.PasswordChangingRepository;
import com.nonfallable.taskKnight.repositories.ProfileRepository;
import com.nonfallable.taskKnight.rest.authentication.dto.ChangePasswordRequestDTO;
import com.nonfallable.taskKnight.rest.authentication.dto.ChangePasswordResponseDTO;
import com.nonfallable.taskKnight.rest.authentication.dto.ConfirmationCodeRequestDTO;
import com.nonfallable.taskKnight.rest.authentication.dto.ConfirmationCodeResponseDTO;
import com.nonfallable.taskKnight.rest.authentication.validators.ChangePasswordRequestValidator;
import com.nonfallable.taskKnight.rest.authentication.validators.ConfirmationCodeRequestRequestValidator;
import com.nonfallable.taskKnight.services.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@Component
public class ChangePasswordRestFacade {

    private ProfileService profileService;
    private ProfileRepository profileRepository;
    private ConfirmationCodeRequestRequestValidator confirmationCodeRequestRequestValidator;
    private ChangePasswordRequestValidator changePasswordRequestValidator;
    private PasswordChangingRepository passwordChangingRepository;
    private PasswordEncoder passwordEncoder;

    public ChangePasswordRestFacade(ProfileService profileService, ProfileRepository profileRepository, ConfirmationCodeRequestRequestValidator confirmationCodeRequestRequestValidator, ChangePasswordRequestValidator changePasswordRequestValidator, PasswordChangingRepository passwordChangingRepository, PasswordEncoder passwordEncoder) {
        this.profileService = profileService;
        this.profileRepository = profileRepository;
        this.confirmationCodeRequestRequestValidator = confirmationCodeRequestRequestValidator;
        this.changePasswordRequestValidator = changePasswordRequestValidator;
        this.passwordChangingRepository = passwordChangingRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public ResponseEntity<ChangePasswordResponseDTO> changePassword(ChangePasswordRequestDTO requestDTO) {
        changePasswordRequestValidator.validate(requestDTO);
        Profile profile = profileService.getAuthorizedUserProfile();
        passwordChangingRepository.save(
                new PasswordChanging()
                        .setNewPassword(passwordEncoder.encode(requestDTO.getNewPassword()))
                        .setSubject(profile)
        );

        return ok(new ChangePasswordResponseDTO(UUID.randomUUID()));
    }

    @Transactional
    public ResponseEntity<ConfirmationCodeResponseDTO> confirmPasswordChanging(UUID token, ConfirmationCodeRequestDTO requestDTO) {
        confirmationCodeRequestRequestValidator.validate(requestDTO);
        Profile profile = profileService.getAuthorizedUserProfile();
        PasswordChanging passwordChanging = passwordChangingRepository.findBySubject(profile)
                .orElseThrow(() -> new NotFoundException("Password changing record not found"));
        profileRepository.save(profile.setPassword(passwordChanging.getNewPassword()));
        passwordChangingRepository.delete(passwordChanging);
        return ok(new ConfirmationCodeResponseDTO("OK"));
    }
}
