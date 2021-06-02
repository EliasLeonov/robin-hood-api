package aseca.roobinhood.api.factory;

import aseca.roobinhood.api.domain.User;
import aseca.roobinhood.api.dto.UserDto;
import aseca.roobinhood.api.dto.security.CreateUserDto;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    public User createUser(CreateUserDto userDto){
        return User.builder()
                .name(userDto.getName())
                .lastname(userDto.getLastname())
                .build();
    }

    public User updateUser(UserDto userDto){
        return User.builder()
                .name(userDto.getName())
                .lastname(userDto.getLastname())
                .build();
    }
}
