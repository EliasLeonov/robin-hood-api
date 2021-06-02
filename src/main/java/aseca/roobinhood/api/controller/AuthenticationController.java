package aseca.roobinhood.api.controller;

import aseca.roobinhood.api.dto.security.AuthenticationRequestDto;
import aseca.roobinhood.api.dto.security.CreateUserDto;
import aseca.roobinhood.api.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestDto authenticationRequestDto) throws BadCredentialsException {
        return authenticationService.createAuthenticationToken(authenticationRequestDto);

    }

    @GetMapping("/validate-token")
    public ResponseEntity<?> tokenValidation(@RequestHeader(value = "Authorization") String token) {
        return authenticationService.tokenValidation(token);
    }

    @PostMapping("/register")
    public void register(@RequestBody CreateUserDto user) {
        authenticationService.register(user);
    }
}
