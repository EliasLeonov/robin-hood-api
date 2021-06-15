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
    private Double accountBalance;
    private String role;

    public static UserDto from(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getUsername(),
                user.getAccountBalance(),
                user.getRole());
    }
}
