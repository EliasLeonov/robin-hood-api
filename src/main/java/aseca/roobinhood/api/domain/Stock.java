package aseca.roobinhood.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
//@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "stock")
public class Stock extends AbstractEntity {

}
