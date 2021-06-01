package aseca.roobinhood.api.domain.dto;

import aseca.roobinhood.api.domain.Stock;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StockDto {
    private Long id;
    private Long value;
    private String company;

    public static StockDto from(Stock stock) {
        return StockDto.builder()
                .id(stock.getId())
                .value(stock.getValue())
                .company(stock.getCompany())
                .build();
    }
}
