package aseca.roobinhood.api.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

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

    public Ticker(Long id, String tickerName, String companyName) {
        super(id);
        this.tickerName = tickerName;
        this.companyName = companyName;
    }
}
