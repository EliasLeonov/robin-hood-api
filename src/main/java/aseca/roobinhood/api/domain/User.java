package aseca.roobinhood.api.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name = "user_data")
public class User extends AbstractEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String role;
    private Double accountBalance;

    public void addAmount(double price) {
        accountBalance += price;
    }

    public void removeAmount(double price) {
        accountBalance -= price;
    }
}
