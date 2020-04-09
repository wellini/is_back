package com.nonfallable.taskKnight.rest.authentication;

import com.nonfallable.taskKnight.models.Profile;
import com.nonfallable.taskKnight.repositories.ProfileRepository;
import com.nonfallable.taskKnight.rest.authentication.dto.AuthenticationRequestDTO;
import com.nonfallable.taskKnight.rest.authentication.dto.AuthenticationResponseDTO;
import com.nonfallable.taskKnight.rest.authentication.exceptions.BadCredentialsSecurityException;
import com.nonfallable.taskKnight.security.JwtUtils;
import com.nonfallable.taskKnight.security.converters.ProfileToUserDetailsConverter;
import com.nonfallable.taskKnight.security.permissions.PermissionsService;
import com.nonfallable.taskKnight.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

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
    public ResponseEntity<AuthenticationResponseDTO> auth() {
        return ResponseEntity.ok(new AuthenticationResponseDTO());
    }

    @PostMapping("/1.0/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody AuthenticationRequestDTO requestDTO) {
        authenticate(requestDTO.getLogin(), requestDTO.getPassword());
        Profile userProfile = profileRepository.findByEmail(requestDTO.getLogin()).get();
        UserDetails userDetails = profileToUserDetailsConverter.toUserDetails(userProfile);
        String token = jwtUtils.generateToken(userDetails.getUsername(), LocalDateTime.now());
        AuthenticationResponseDTO responseDTO = new AuthenticationResponseDTO()
                .setId(userProfile.getId())
                .setExpires(jwtUtils.getExp(token).format(DateTimeUtil.DATE_TIME_FORMATTER))
                .setLogin(userProfile.getEmail())
                .setPermissions(permissionsService.getPermissionsByRole(userProfile.getRole()))
                .setAccessToken(token);
        return ResponseEntity.ok(responseDTO);
    }

    private void authenticate(String login, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsSecurityException("Login or password are incorrect", ex);
        }
    }
}
