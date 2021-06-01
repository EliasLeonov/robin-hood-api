package aseca.roobinhood.api.factory;

import aseca.roobinhood.api.domain.User;
import aseca.roobinhood.api.domain.dto.CreateUserDto;
import aseca.roobinhood.api.domain.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class UserFactory {
    public static User createUser(CreateUserDto userDto){
        return User.builder()
                .name(userDto.getName())
                .lastname(userDto.getLastname())
                .stoncks(new HashSet<>())
                .build();
    }
    public static User updateUser(UserDto userDto){
        return User
                .builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .lastname(userDto.getLastname())
                .stoncks(userDto
                        .getStocks()
                        .stream()
                        .map(StockFactory::updateStock)
                        .collect(Collectors.toSet()))
                .build();
    }
}
