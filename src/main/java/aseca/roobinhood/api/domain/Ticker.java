package aseca.roobinhood.api.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "ticker")
public class Ticker extends AbstractEntity {
    private String companyName;

    @ManyToOne(cascade = CascadeType.ALL)
    private Broker broker;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ticker")
    private Set<Transaction> transactions = new HashSet<>();

}
