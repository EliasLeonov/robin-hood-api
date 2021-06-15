package aseca.roobinhood.api.service;

import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;

@Service
public class StockScrapperService {

    public Stock getStock(String companyTicker) throws IOException {
        return YahooFinance.get(companyTicker);
    }

    public double getPrice(String companyTicker) throws IOException {
        return getStock(companyTicker).getQuote().getPrice().doubleValue();
    }

}
