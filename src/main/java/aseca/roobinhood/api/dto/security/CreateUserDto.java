package aseca.roobinhood.api.dto.security;

import aseca.roobinhood.api.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserDto {
    private String name;
    private String lastname;
    private String email;
    private String username;
    private String password;

    public static User from(CreateUserDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .lastname(userDto.getLastname())
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .accountBalance(100000)
                .role("ROLE_USER")
                .build();
    }
}
