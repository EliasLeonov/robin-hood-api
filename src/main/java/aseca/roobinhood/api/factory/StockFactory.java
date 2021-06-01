package aseca.roobinhood.api.factory;

import aseca.roobinhood.api.domain.Stoncks;
import aseca.roobinhood.api.domain.dto.StockDto;

public class StockFactory {
    public static Stoncks updateStock(StockDto stockDto){
        return Stoncks.builder()
                .id(stockDto.getId())
                .company(stockDto.getCompany())
                .value(stockDto.getValue())
                .build();
    }
}
