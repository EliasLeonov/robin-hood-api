package aseca.roobinhood.api.dto;

import aseca.roobinhood.api.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String lastname;
    private String email;
    private String username;
    private String role;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .lastname(user.getLastname())
                .build();
    }
}
