package aseca.roobinhood.api.domain;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "stock")
public class Stock extends AbstractEntity {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stock")
    private Set<Transaction> transactions = new HashSet<>();

}
