package aseca.roobinhood.api.dto;

import aseca.roobinhood.api.domain.Ticker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDto {
    private Ticker ticker;
    private String name;
    private Long price;
    private Double percentage;
}
