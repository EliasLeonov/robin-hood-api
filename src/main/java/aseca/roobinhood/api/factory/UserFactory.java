package aseca.roobinhood.api.factory;

import aseca.roobinhood.api.domain.User;
import aseca.roobinhood.api.dto.UserDto;
import aseca.roobinhood.api.dto.security.CreateUserDto;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    public User createUser(CreateUserDto userDto){
        return User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .role("Normal")
                .build();
    }

    public User updateUser(UserDto userDto){
        return User.builder()
                .firstName(userDto.getName())
                .lastName(userDto.getLastname())
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .role(userDto.getRole())
                .build();
    }
}
