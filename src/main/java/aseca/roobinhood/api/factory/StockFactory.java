package aseca.roobinhood.api.factory;

import aseca.roobinhood.api.domain.Stock;
import aseca.roobinhood.api.dto.StockDto;
import org.springframework.stereotype.Component;

@Component
public class StockFactory {
    public static Stock updateStock(StockDto stockDto){
        return Stock.builder().build();
    }
}
