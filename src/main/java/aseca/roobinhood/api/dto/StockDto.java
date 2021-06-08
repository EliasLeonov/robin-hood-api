package aseca.roobinhood.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StockDto {
    private double value;
    private double amount;
    private String tickerName;
    private String companyName;
}
