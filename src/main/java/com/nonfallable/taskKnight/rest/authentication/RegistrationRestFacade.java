package com.nonfallable.taskKnight.rest.authentication;

import com.nonfallable.taskKnight.models.ConfirmationToken;
import com.nonfallable.taskKnight.models.ConfirmationTokenType;
import com.nonfallable.taskKnight.models.Profile;
import com.nonfallable.taskKnight.models.ProfileStatus;
import com.nonfallable.taskKnight.repositories.ProfileRepository;
import com.nonfallable.taskKnight.rest.authentication.converters.RegistrationRequestToProfileConverter;
import com.nonfallable.taskKnight.rest.authentication.dto.ConfirmationCodeRequestDTO;
import com.nonfallable.taskKnight.rest.authentication.dto.ConfirmationCodeResponseDTO;
import com.nonfallable.taskKnight.rest.authentication.dto.RegistrationRequestDTO;
import com.nonfallable.taskKnight.rest.authentication.dto.RegistrationResponseDTO;
import com.nonfallable.taskKnight.rest.authentication.exceptions.ProfileWithSameLoginExistsException;
import com.nonfallable.taskKnight.rest.authentication.validators.ConfirmationCodeRequestRequestValidator;
import com.nonfallable.taskKnight.rest.authentication.validators.RegistrationRequestValidator;
import com.nonfallable.taskKnight.security.AccessToken;
import com.nonfallable.taskKnight.security.JwtUtils;
import com.nonfallable.taskKnight.security.permissions.Permission;
import com.nonfallable.taskKnight.security.permissions.PermissionsService;
import com.nonfallable.taskKnight.services.ConfirmationTokenService;
import com.nonfallable.taskKnight.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@Component
public class RegistrationRestFacade {

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private RegistrationRequestToProfileConverter registrationRequestToProfileConverter;

    @Autowired
    private RegistrationRequestValidator registrationRequestValidator;

    @Autowired
    private ConfirmationCodeRequestRequestValidator confirmationCodeRequestRequestValidator;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PermissionsService permissionsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Transactional
    public ResponseEntity<RegistrationResponseDTO> registration(RegistrationRequestDTO requestDTO) {
        registrationRequestValidator.validate(requestDTO);
        checkThatProfileDoesNotExist(requestDTO.getLogin());
        Profile profile = profileRepository.save(registrationRequestToProfileConverter.toDomain(requestDTO));
        ConfirmationToken confirmationToken = confirmationTokenService.createToken(profile.getEmail(), ConfirmationTokenType.REGISTRATION);
        AccessToken accessToken = jwtUtils.generateToken(profile);
        List<Permission> permissions = permissionsService.getPermissionsByRole(profile.getRole());
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(profile.getEmail(), profile.getPassword(), permissions));
        RegistrationResponseDTO registrationResponseDTO = new RegistrationResponseDTO()
                .setLogin(profile.getEmail())
                .setConfirmationToken(confirmationToken.getId().toString())
                .setAccessToken(accessToken.getToken())
                .setExpires(accessToken.getExpiredAt().format(DateTimeUtil.DATE_TIME_FORMATTER))
                .setPermissions(permissions);
        return ok(registrationResponseDTO);
    }

    private void checkThatProfileDoesNotExist(String email) {
        if (profileRepository.findByEmail(email).isPresent()) throw new ProfileWithSameLoginExistsException();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<ConfirmationCodeResponseDTO> confirmRegistration(UUID token, ConfirmationCodeRequestDTO requestDTO) {
        confirmationCodeRequestRequestValidator.validate(requestDTO);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        confirmationTokenService.confirm(token, requestDTO.getCode(), userDetails.getUsername(), ConfirmationTokenType.REGISTRATION);
        Profile profile = profileRepository.findByEmail(userDetails.getUsername()).get();
        profileRepository.save(profile.setStatus(ProfileStatus.ACTIVATED));
        return ok(new ConfirmationCodeResponseDTO().setMessage("OK"));
    }
}
