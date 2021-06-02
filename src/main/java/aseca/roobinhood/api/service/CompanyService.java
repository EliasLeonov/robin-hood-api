package aseca.roobinhood.api.service;

import aseca.roobinhood.api.dto.CompanyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    private final CompanyService companyService;
    private final StockScrapperService stockScrapperService;
    private final static List<String> tickers = Arrays.asList("AAPL", "TSLA", "MSFT", "BB", "GME", "AMC");

    @Autowired
    public CompanyService(CompanyService companyService, StockScrapperService stockScrapperService) {
        this.companyService = companyService;
        this.stockScrapperService = stockScrapperService;
    }

    public CompanyDto getCompanyById(Long id) {
        return CompanyDto.builder().build();
    }

    public List<CompanyDto> getAllCompanies() {
        return tickers.stream().map(ticker -> {
            try {
                final Stock stock = stockScrapperService.getStock(ticker);
                return new CompanyDto(ticker, stock.getQuote().getPrice().doubleValue(), stock.getQuote().getPriceAvg200().doubleValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
    }
}
