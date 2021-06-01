package aseca.roobinhood.api.factory;

import aseca.roobinhood.api.domain.Stock;
import aseca.roobinhood.api.domain.dto.StockDto;

public class StockFactory {
    public static Stock updateStock(StockDto stockDto){
        return Stock.builder()
                .company(stockDto.getCompany())
                .value(stockDto.getValue())
                .build();
    }
}
