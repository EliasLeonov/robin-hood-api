package aseca.roobinhood.api.utils;

import aseca.roobinhood.api.domain.User;
import aseca.roobinhood.api.exceptions.UnauthorizedException;
import aseca.roobinhood.api.repository.UserRepository;
import io.jsonwebtoken.impl.DefaultClaims;
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



    public User findLogged(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(" jfd;lfadf");
//        return null;
        String username = ((org.springframework.security.core.userdetails.User) authentication.getPrincipal()).getUsername();
        return userRepository.findByUsername(username).get();
    }
}
