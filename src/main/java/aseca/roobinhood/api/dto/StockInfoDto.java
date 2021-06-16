package aseca.roobinhood.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StockInfoDto {
    private double actualTotal;
    private double amount;
    private String tickerName;
    private String companyName;
    private double purchasePrice;
    private double actualPrice;
    private double result;
}
