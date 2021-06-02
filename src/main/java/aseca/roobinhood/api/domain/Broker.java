package aseca.roobinhood.api.domain;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "broker")
public class Broker extends AbstractEntity {
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "broker")
    private Set<Ticker> tickers = new HashSet<>();
}
