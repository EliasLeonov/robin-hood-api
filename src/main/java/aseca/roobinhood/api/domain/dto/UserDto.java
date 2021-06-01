package aseca.roobinhood.api.domain.dto;

import aseca.roobinhood.api.domain.Stock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private String id;
    private String name;
    private String lastname;
    private List<Stock> stocks;
}
