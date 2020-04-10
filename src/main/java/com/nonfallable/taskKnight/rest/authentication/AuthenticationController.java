package com.nonfallable.taskKnight.rest.authentication;

import com.nonfallable.taskKnight.models.Profile;
import com.nonfallable.taskKnight.repositories.ProfileRepository;
import com.nonfallable.taskKnight.rest.authentication.dto.AuthenticationRequestDTO;
import com.nonfallable.taskKnight.rest.authentication.dto.AuthenticationResponseDTO;
import com.nonfallable.taskKnight.rest.authentication.exceptions.BadCredentialsSecurityException;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileToUserDetailsConverter profileToUserDetailsConverter;

    @Autowired
    private PermissionsService permissionsService;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/1.0/auth")
    public ResponseEntity<AuthenticationResponseDTO> auth(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = ((UserDetails) authentication.getPrincipal()).getUsername();
        Profile profile = profileRepository.findByEmail(email).get();
        String token = request.getHeader("Authorization");
        AuthenticationResponseDTO responseDTO = createAuthenticationResponseDTO(
                profile.getId(),
                profile.getEmail(),
                jwtUtils.getExp(token).format(DateTimeUtil.DATE_TIME_FORMATTER),
                token,
                permissionsService.getPermissionsByRole(profile.getRole())
        );
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/1.0/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody AuthenticationRequestDTO requestDTO) {
        Profile userProfile = profileRepository.findByEmail(requestDTO.getLogin()).get();
        List<Permission> permissions = permissionsService.getPermissionsByRole(userProfile.getRole());

        authenticate(requestDTO.getLogin(), requestDTO.getPassword(), permissions);

        UserDetails userDetails = profileToUserDetailsConverter.toUserDetails(userProfile);
        String token = jwtUtils.generateToken(userProfile.getId(), userDetails.getUsername(), LocalDateTime.now().plusDays(10));
        AuthenticationResponseDTO responseDTO = createAuthenticationResponseDTO(
                userProfile.getId(),
                userProfile.getEmail(),
                jwtUtils.getExp(token).format(DateTimeUtil.DATE_TIME_FORMATTER),
                token,
                permissions
        );
        return ResponseEntity.ok(responseDTO);
    }

    private AuthenticationResponseDTO createAuthenticationResponseDTO(
            UUID id,
            String login,
            String expiresAt,
            String accessToken,
            List<Permission> permissions
    ){
        return new AuthenticationResponseDTO()
                .setId(id)
                .setLogin(login)
                .setExpires(expiresAt)
                .setAccessToken(accessToken)
                .setPermissions(permissions);
    }

    private void authenticate(String login, String password, List<Permission> permissions) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password, permissions));
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsSecurityException("Login or password are incorrect", ex);
        }
    }
}
