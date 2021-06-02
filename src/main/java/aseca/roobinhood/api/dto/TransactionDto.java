package aseca.roobinhood.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private TickerDto tickerDto;
    private Long userId;
    private Long amount;
    private Long price;
}
