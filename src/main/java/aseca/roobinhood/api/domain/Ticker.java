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
@Table(name = "ticker")
public class Ticker extends AbstractEntity {
    private String tickerName;
    private String companyName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ticker")
    private Set<Transaction> transactions = new HashSet<>();

    public Ticker(String tickerName, String companyName) {
        this.tickerName = tickerName;
        this.companyName = companyName;
    }
}
