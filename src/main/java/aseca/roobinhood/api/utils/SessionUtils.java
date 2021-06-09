package aseca.roobinhood.api.utils;

import aseca.roobinhood.api.domain.User;
import aseca.roobinhood.api.domain.auth.MyUserDetails;
import aseca.roobinhood.api.exceptions.NotFoundException;
import aseca.roobinhood.api.exceptions.UnauthorizedException;
import aseca.roobinhood.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SessionUtils {

    private final UserRepository userRepository;

    @Autowired
    public SessionUtils(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getTokenUserInformation() {
        Authentication jwt = SecurityContextHolder.getContext().getAuthentication();
        if (jwt == null) throw new UnauthorizedException("Error while getting session token");
        MyUserDetails user = (MyUserDetails) jwt.getPrincipal();
        Optional<User> found = this.userRepository.findByUsername(user.getUsername());
        if (found.isEmpty()) throw new NotFoundException("Token user not found");
        return found.get();
    }
}
