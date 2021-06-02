package aseca.roobinhood.api.domain;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "transaction")
public class Transaction extends AbstractEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    private Ticker ticker;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    private double price;
    private double amount;
    private LocalDateTime dateTime;
}
