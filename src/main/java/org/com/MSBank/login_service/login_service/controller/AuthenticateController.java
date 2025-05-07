package org.com.MSBank.login_service.login_service.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.com.MSBank.login_service.login_service.service.AuthenticationService;
import org.com.MSBank.login_service.login_service.view.CredentialDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/login")
public class AuthenticateController {

    private final AuthenticationService authenticationService;

    public AuthenticateController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<HttpStatus> login (HttpServletResponse respone,
            @RequestBody CredentialDto credentialDto) {

        return ResponseEntity.ok()
                .header("Authenticate", authenticationService.authenticate(credentialDto)).build();
    }
}
