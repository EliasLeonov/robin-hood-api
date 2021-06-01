package aseca.roobinhood.api.service;

import aseca.roobinhood.api.domain.dto.CreateUserDto;
import aseca.roobinhood.api.domain.dto.UserDto;
import aseca.roobinhood.api.factory.UserFactory;
import aseca.roobinhood.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    public UserDto save(CreateUserDto userDto){
        return repository
                .save(UserFactory.createUser(userDto))
                .toDto();
    }

    public UserDto update(UserDto userDto){
        return repository
                .save(UserFactory.updateUser(userDto))
                .toDto();
    }

    public Boolean delete(Long id){
        repository.deleteById(id);
        return repository.existsById(id);
    }


}