package aseca.roobinhood.api.service;

import aseca.roobinhood.api.domain.User;
import aseca.roobinhood.api.domain.auth.MyUserDetails;
import aseca.roobinhood.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    final UserRepository userRepository;

    @Autowired
    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(userName);
        if (user.isPresent()) return new MyUserDetails(user.get());
        throw new UsernameNotFoundException(userName + "not found");
    }
}
