package aseca.roobinhood.api.service;

import aseca.roobinhood.api.dto.CompanyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.quotes.stock.StockQuote;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    private final StockScrapperService stockScrapperService;

    @Autowired
    public CompanyService(StockScrapperService stockScrapperService) {
        this.stockScrapperService = stockScrapperService;
    }

    public CompanyDto getCompanyById(Long id) {
        return CompanyDto.builder().build();
    }

    public List<CompanyDto> getAllCompanies() throws IOException {
        return SP500Companies().stream().map(ticker -> {
            try {
                final Stock stock = stockScrapperService.getStock(ticker);
                final StockQuote quote = stock.getQuote();
                return new CompanyDto(ticker, stock.getName(), quote.getPrice().doubleValue(), quote.getChangeInPercent().doubleValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public List<String> SP500Companies() throws IOException {
        List<String> indexes = new ArrayList<>();
        BufferedReader csvReader = new BufferedReader(new FileReader("src/main/resources/sp500_tickers.csv"));
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            indexes.add(data[0]);
        }
        csvReader.close();
        return indexes;
    }
}
