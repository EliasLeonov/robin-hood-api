package aseca.roobinhood.api.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

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
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Stock> stocks;
}
