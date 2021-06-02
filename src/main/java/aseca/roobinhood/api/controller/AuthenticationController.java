package aseca.roobinhood.api.controller;

import aseca.roobinhood.api.domain.AuthenticationRequest;
import aseca.roobinhood.api.domain.dto.CreateUserDto;
import aseca.roobinhood.api.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.BadCredentialsException;

@RestController
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws BadCredentialsException {
        return authenticationService.createAuthenticationToken(authenticationRequest);

    }

    @GetMapping(value = "/validate-token")
    public ResponseEntity<?> tokenValidation(@RequestHeader(value = "Authorization") String token){
        return authenticationService.tokenValidation(token);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public void register(@RequestBody CreateUserDto user){
        authenticationService.register(user);
    }
}
