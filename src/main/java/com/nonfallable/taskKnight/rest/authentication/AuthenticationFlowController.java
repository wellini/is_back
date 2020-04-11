package com.nonfallable.taskKnight.rest.authentication;

import com.nonfallable.taskKnight.rest.authentication.dto.AuthenticationRequestDTO;
import com.nonfallable.taskKnight.rest.authentication.dto.AuthenticationResponseDTO;
import com.nonfallable.taskKnight.rest.authentication.dto.RegistrationConfirmationRequestDTO;
import com.nonfallable.taskKnight.rest.authentication.dto.RegistrationConfirmationResponseDTO;
import com.nonfallable.taskKnight.rest.authentication.dto.RegistrationRequestDTO;
import com.nonfallable.taskKnight.rest.authentication.dto.RegistrationResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
public class AuthenticationFlowController {

    @Autowired
    private AuthenticationRestFacade authenticationRestFacade;

    @Autowired
    private RegistrationRestFacade registrationRestFacade;

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
    public ResponseEntity<RegistrationConfirmationResponseDTO> confirmRegistration(@PathVariable UUID token, @RequestBody RegistrationConfirmationRequestDTO requestDTO) {
        return registrationRestFacade.confirmRegistration(token, requestDTO);
    }
}
