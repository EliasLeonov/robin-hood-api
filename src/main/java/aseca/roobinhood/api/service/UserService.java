package aseca.roobinhood.api.service;

import aseca.roobinhood.api.domain.User;
import aseca.roobinhood.api.dto.UserDto;
import aseca.roobinhood.api.exceptions.BadRequestException;
import aseca.roobinhood.api.factory.UserFactory;
import aseca.roobinhood.api.repository.UserRepository;
import aseca.roobinhood.api.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserFactory userFactory;
    private final SessionUtils sessionUtils;

    @Autowired
    public UserService(UserRepository userRepository, UserFactory userFactory, SessionUtils sessionUtils) {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
        this.sessionUtils = sessionUtils;
    }

    public UserDto update(UserDto userDto) {
        return UserDto.from(userRepository.save(userFactory.updateUser(userDto)));
    }

    public boolean delete(Long id) {
        userRepository.deleteById(id);
        return userRepository.existsById(id);
    }

    public void removeAmount(double price) {
        final User user = sessionUtils.getTokenUserInformation();
        if (user.getAccountBalance() >= price) user.removeAmount(price);
        else throw new BadRequestException("User has no balance");
        userRepository.save(user);
    }

    public UserDto getUserLogged() {
        final User user = sessionUtils.getTokenUserInformation();
        return UserDto.from(user);
    }
}