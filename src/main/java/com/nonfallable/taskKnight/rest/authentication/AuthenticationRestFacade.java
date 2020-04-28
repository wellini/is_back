package com.nonfallable.taskKnight.rest.authentication;

import com.nonfallable.taskKnight.models.Profile;
import com.nonfallable.taskKnight.repositories.ProfileRepository;
import com.nonfallable.taskKnight.rest.authentication.dto.AuthenticationRequestDTO;
import com.nonfallable.taskKnight.rest.authentication.dto.AuthenticationResponseDTO;
import com.nonfallable.taskKnight.rest.authentication.exceptions.BadCredentialsManagedSecurityException;
import com.nonfallable.taskKnight.rest.authentication.validators.AuthenticationRequestValidator;
import com.nonfallable.taskKnight.security.AccessToken;
import com.nonfallable.taskKnight.security.JwtUtils;
import com.nonfallable.taskKnight.security.converters.ProfileToUserDetailsConverter;
import com.nonfallable.taskKnight.security.permissions.Permission;
import com.nonfallable.taskKnight.security.permissions.PermissionsService;
import com.nonfallable.taskKnight.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Component
public class AuthenticationRestFacade {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileToUserDetailsConverter profileToUserDetailsConverter;

    @Autowired
    private PermissionsService permissionsService;

    @Autowired
    private AuthenticationRequestValidator authenticationRequestValidator;

    @Autowired
    private JwtUtils jwtUtils;

    public ResponseEntity<AuthenticationResponseDTO> auth(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = ((UserDetails) authentication.getPrincipal()).getUsername();
        Profile profile = profileRepository.findByEmail(email).get();
        AccessToken token = jwtUtils.decode(request.getHeader("Authorization"));

        AuthenticationResponseDTO responseDTO = createAuthenticationResponseDTO(
                profile.getId(),
                profile.getEmail(),
                token,
                permissionsService.getPermissionsByRole(profile.getRole())
        );
        return ResponseEntity.ok(responseDTO);
    }

    public ResponseEntity<AuthenticationResponseDTO> login(AuthenticationRequestDTO requestDTO) {
        authenticationRequestValidator.validate(requestDTO);
        Profile userProfile = profileRepository.findByEmail(requestDTO.getLogin())
                .orElseThrow(() -> new BadCredentialsManagedSecurityException());
        List<Permission> permissions = permissionsService.getPermissionsByRole(userProfile.getRole());

        authenticate(requestDTO.getLogin(), requestDTO.getPassword(), permissions);
        AccessToken token = jwtUtils.generateToken(userProfile);

        AuthenticationResponseDTO responseDTO = createAuthenticationResponseDTO(
                userProfile.getId(),
                userProfile.getEmail(),
                token,
                permissions
        );
        return ResponseEntity.ok(responseDTO);
    }

    private AuthenticationResponseDTO createAuthenticationResponseDTO(
            UUID id,
            String login,
            AccessToken accessToken,
            List<Permission> permissions
    ) {
        return new AuthenticationResponseDTO()
                .setId(id)
                .setLogin(login)
                .setExpires(accessToken.getExpiredAt().format(DateTimeUtil.DATE_TIME_FORMATTER))
                .setAccessToken(accessToken.getToken())
                .setPermissions(permissions);
    }

    private void authenticate(String login, String password, List<Permission> permissions) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password, permissions));
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsManagedSecurityException(ex);
        }
    }
}
