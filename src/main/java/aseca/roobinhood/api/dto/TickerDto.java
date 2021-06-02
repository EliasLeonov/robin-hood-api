package aseca.roobinhood.api.dto;

import aseca.roobinhood.api.domain.Ticker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TickerDto {
    private Long id;
    private String companyName;

    public static TickerDto from(Ticker ticker){
        return TickerDto
                .builder()
                .id(ticker.getId())
                .companyName(ticker.getCompanyName())
                .build();
    }
}
