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
@Table(name = "stocks_data")
public class Stock extends AbstractEntity {
    private Long value;
    private String company;
}
