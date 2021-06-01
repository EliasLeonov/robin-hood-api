package aseca.roobinhood.api.service;

import aseca.roobinhood.api.domain.dto.CreateUserDto;
import aseca.roobinhood.api.domain.dto.UserDto;
import aseca.roobinhood.api.factory.UserFactory;
import aseca.roobinhood.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto save(CreateUserDto userDto){
        return UserDto.from(userRepository.save(UserFactory.createUser(userDto)));
    }

    public UserDto update(UserDto userDto){
        return UserDto.from(userRepository.save(UserFactory.updateUser(userDto)));
    }

    public boolean delete(Long id){
        userRepository.deleteById(id);
        return userRepository.existsById(id);
    }


}