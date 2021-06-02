package aseca.roobinhood.api.service;

import aseca.roobinhood.api.dto.UserDto;
import aseca.roobinhood.api.dto.security.CreateUserDto;
import aseca.roobinhood.api.factory.UserFactory;
import aseca.roobinhood.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserFactory userFactory;

    @Autowired
    public UserService(UserRepository userRepository, UserFactory userFactory) {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
    }

    public UserDto save(CreateUserDto userDto){
        return UserDto.from(userRepository.save(userFactory.createUser(userDto)));
    }

    public UserDto update(UserDto userDto){
        return UserDto.from(userRepository.save(userFactory.updateUser(userDto)));
    }

    public boolean delete(Long id){
        userRepository.deleteById(id);
        return userRepository.existsById(id);
    }


}