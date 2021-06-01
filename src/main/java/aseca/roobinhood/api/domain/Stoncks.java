package aseca.roobinhood.api.domain;

import aseca.roobinhood.api.domain.dto.StockDto;
import aseca.roobinhood.api.domain.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "stoncks_data")
public class Stoncks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long value;
    private String company;

    public StockDto toDto(){
       return  StockDto
               .builder()
               .company(company)
               .value(value)
               .build();
    }
}
