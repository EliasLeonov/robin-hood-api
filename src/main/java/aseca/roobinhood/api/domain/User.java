package aseca.roobinhood.api.domain;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @Builder.Default
    private Set<Transaction> transactions = new HashSet<>();

    public void addAmount(double price) {
        accountBalance += price;
    }

    public void removeAmount(double price) {
        accountBalance -= price;
    }
}
