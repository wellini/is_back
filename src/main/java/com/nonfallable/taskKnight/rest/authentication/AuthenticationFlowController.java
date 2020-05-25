package com.nonfallable.taskKnight.rest.authentication;

import com.nonfallable.taskKnight.rest.authentication.dto.AuthenticationRequestDTO;
import com.nonfallable.taskKnight.rest.authentication.dto.AuthenticationResponseDTO;
import com.nonfallable.taskKnight.rest.authentication.dto.ChangePasswordRequestDTO;
import com.nonfallable.taskKnight.rest.authentication.dto.ChangePasswordResponseDTO;
import com.nonfallable.taskKnight.rest.authentication.dto.ConfirmationCodeRequestDTO;
import com.nonfallable.taskKnight.rest.authentication.dto.ConfirmationCodeResponseDTO;
import com.nonfallable.taskKnight.rest.authentication.dto.RegistrationRequestDTO;
import com.nonfallable.taskKnight.rest.authentication.dto.RegistrationResponseDTO;
import com.nonfallable.taskKnight.rest.dto.ApiErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
public class AuthenticationFlowController {

    @Autowired
    private AuthenticationRestFacade authenticationRestFacade;

    @Autowired
    private RegistrationRestFacade registrationRestFacade;

    @Autowired
    private ChangePasswordRestFacade changePasswordRestFacade;

    @GetMapping("/1.0/auth")
    public ResponseEntity<AuthenticationResponseDTO> auth(HttpServletRequest request) {
        return authenticationRestFacade.auth(request);
    }

    @PostMapping("/1.0/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody AuthenticationRequestDTO requestDTO) {
        return authenticationRestFacade.login(requestDTO);
    }

    @PostMapping("/1.0/registration")
    public ResponseEntity<RegistrationResponseDTO> registration(@RequestBody RegistrationRequestDTO requestDTO) {
        return registrationRestFacade.registration(requestDTO);
    }

    @PostMapping("/1.0/registration/confirm/{token}")
    public ResponseEntity<ConfirmationCodeResponseDTO> confirmRegistration(@PathVariable UUID token, @RequestBody ConfirmationCodeRequestDTO requestDTO) {
        return registrationRestFacade.confirmRegistration(token, requestDTO);
    }

    @PostMapping("/1.0/change-password")
    public ResponseEntity<ChangePasswordResponseDTO> changePassword(@RequestBody ChangePasswordRequestDTO requestDTO) {
        return changePasswordRestFacade.changePassword(requestDTO);
    }

    @PostMapping("/1.0/change-password/confirm/{token}")
    public ResponseEntity<ConfirmationCodeResponseDTO> confirmPasswordChanging(@PathVariable UUID token, @RequestBody ConfirmationCodeRequestDTO requestDTO) {
        return changePasswordRestFacade.confirmPasswordChanging(token, requestDTO);
    }

    @RequestMapping("/1.0/access-denied")
    public ResponseEntity<ApiErrorDTO> accessDenied() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiErrorDTO().setMessage("Unauthorized-/1.0/access-denied"));
    }
}
