package com.nonfallable.taskKnight.rest.profile;

import com.nonfallable.taskKnight.exceptions.NotFoundException;
import com.nonfallable.taskKnight.models.Profile;
import com.nonfallable.taskKnight.repositories.ProfileRepository;
import com.nonfallable.taskKnight.rest.dto.ApiSuccessDTO;
import com.nonfallable.taskKnight.rest.dto.PageDTO;
import com.nonfallable.taskKnight.rest.profile.converters.ProfileRequestConverter;
import com.nonfallable.taskKnight.rest.profile.converters.ProfileResponseConverter;
import com.nonfallable.taskKnight.rest.profile.dto.ProfileRequestDTO;
import com.nonfallable.taskKnight.rest.profile.dto.ProfileResponseDTO;
import com.nonfallable.taskKnight.rest.profile.validators.SaveProfileRequestValidator;
import com.nonfallable.taskKnight.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class ProfilesController {

    @Autowired
    private ProfileResponseConverter profileResponseConverter;

    @Autowired
    private ProfileRequestConverter profileRequestConverter;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private SaveProfileRequestValidator saveProfileRequestValidator;

    @GetMapping("/1.0/iam")
    public ResponseEntity<ProfileResponseDTO> iam() {
        Profile authorizedUserProfile = profileService.getAuthorizedUserProfile();
        return ok(profileResponseConverter.fromDomain(authorizedUserProfile));
    }

    @GetMapping("/1.0/listProfiles")
    public ResponseEntity<PageDTO<ProfileResponseDTO>> listProfiles(@PageableDefault Pageable pageable) {
        Page<Profile> profiles = profileRepository.findAll(pageable);
        return ok(profileResponseConverter.fromDomain(profiles));
    }

    @GetMapping("/1.0/getProfile/{profileId}")
    public ResponseEntity<ProfileResponseDTO> getProfile(@PathVariable UUID profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new NotFoundException("Profile not found"));
        return ok(profileResponseConverter.fromDomain(profile));
    }

    @PostMapping("/1.0/updateMyProfile")
    public ResponseEntity<ProfileResponseDTO> updateProfile(@RequestBody ProfileRequestDTO dto) {
        saveProfileRequestValidator.validate(dto);
        Profile authorizedUserProfile = profileService.getAuthorizedUserProfile();
        Profile update = profileRequestConverter.toDomain(dto);
        authorizedUserProfile
                .setName(update.getName())
                .setEmail(update.getEmail());
        profileRepository.save(authorizedUserProfile);
        return ok(profileResponseConverter.fromDomain(authorizedUserProfile));
    }

    @PostMapping("/1.0/updateProfile/{profileId}")
    public ResponseEntity<ProfileResponseDTO> updateProfile(@PathVariable UUID profileId, @RequestBody ProfileRequestDTO dto) {
        Profile profile = profileRepository.findAndLockById(profileId)
                .orElseThrow(() -> new NotFoundException("Profile not found"));
        saveProfileRequestValidator.validate(dto);
        Profile update = profileRequestConverter.toDomain(dto);
        profile.setName(update.getName())
                .setEmail(update.getEmail());
        profileRepository.save(profile);
        return ok(profileResponseConverter.fromDomain(profile));
    }

    @DeleteMapping("/1.0/deleteMyProfile")
    public ResponseEntity<ApiSuccessDTO> deleteMyProfile() {
        Profile authorizedUserProfile = profileService.getAuthorizedUserProfile();
        profileService.completeDeletion(authorizedUserProfile);
        return ok(new ApiSuccessDTO().setMessage("Success deletion"));
    }

    @DeleteMapping("/1.0/deleteProfile/{profileId}")
    public ResponseEntity<ApiSuccessDTO> deleteMyProfile(@PathVariable UUID profileId) {
        Profile profile = profileRepository.findAndLockById(profileId)
                .orElseThrow(() -> new NotFoundException("Profile not found"));
        profileService.completeDeletion(profile);
        return ok(new ApiSuccessDTO().setMessage("Success deletion"));
    }
}
