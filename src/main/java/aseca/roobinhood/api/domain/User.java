package aseca.roobinhood.api.domain;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name = "user_data")
public class User extends AbstractEntity {
    private String name;
    private String lastname;
    private String email;
    private String username;
    private String password;
    private String role;
}
