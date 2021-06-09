package aseca.roobinhood.api.service;

import aseca.roobinhood.api.domain.User;
import aseca.roobinhood.api.dto.UserDto;
import aseca.roobinhood.api.dto.security.AuthenticationRequestDto;
import aseca.roobinhood.api.dto.security.AuthenticationResponseDto;
import aseca.roobinhood.api.dto.security.CreateUserDto;
import aseca.roobinhood.api.dto.security.RoleResponseDto;
import aseca.roobinhood.api.repository.UserRepository;
import aseca.roobinhood.api.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService myUserDetailsService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    final static double DEFAULT_USER_BALANCE = 100000;

    @Autowired
    public AuthenticationService(AuthenticationManager authenticationManager, MyUserDetailsService myUserDetailsService, UserRepository userRepository, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.myUserDetailsService = myUserDetailsService;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public ResponseEntity<?> createAuthenticationToken(AuthenticationRequestDto authenticationRequestDto) throws BadCredentialsException {
        //Could throw BadCredentialsException
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequestDto.getUsername(), authenticationRequestDto.getPassword())
        );

        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequestDto.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponseDto(jwt));
    }

    public ResponseEntity<?> tokenValidation(String token) {
        String role = jwtUtil.extractRole(token.substring(6));
        return ResponseEntity.ok(new RoleResponseDto(role));
    }

    public UserDto register(CreateUserDto userDto) {
        final User user = CreateUserDto.from(userDto);
        user.setAccountBalance(DEFAULT_USER_BALANCE);
        return UserDto.from(userRepository.save(user));
    }
}
