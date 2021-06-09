package aseca.roobinhood.api.service;

import aseca.roobinhood.api.domain.Ticker;
import aseca.roobinhood.api.dto.CompanyDto;
import aseca.roobinhood.api.repository.TickerRepository;
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
    private final TickerRepository tickerRepository;

    @Autowired
    public CompanyService(StockScrapperService stockScrapperService, TickerRepository tickerRepository) {
        this.stockScrapperService = stockScrapperService;
        this.tickerRepository = tickerRepository;
    }

    public List<CompanyDto> getAllCompanies() throws IOException {
        final List<Ticker> tickers = SP500Companies().stream().map(ticker ->
                tickerRepository.findByTickerName(ticker.getTickerName()).orElseGet(() -> tickerRepository.save(ticker)))
                .collect(Collectors.toList());
        return tickers.stream().map(ticker -> {
            try {
                final Stock stock = stockScrapperService.getStock(ticker.getTickerName());
                final StockQuote quote = stock.getQuote();
                return new CompanyDto(ticker.getId(), ticker.getTickerName(), stock.getName(), quote.getPrice().doubleValue(), quote.getChangeInPercent().doubleValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public List<Ticker> SP500Companies() throws IOException {
        List<Ticker> indexes = new ArrayList<>();
        BufferedReader csvReader = new BufferedReader(new FileReader("src/main/resources/sp500_tickers.csv"));
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            indexes.add(new Ticker(data[0], data[1]));
        }
        csvReader.close();
        return indexes;
    }
}
