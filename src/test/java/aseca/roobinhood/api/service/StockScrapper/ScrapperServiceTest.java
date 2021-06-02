package aseca.roobinhood.api.service.StockScrapper;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;

@RunWith(SpringRunner.class)
@ActiveProfiles("mem")
@SpringBootTest
public class ScrapperServiceTest {

    @Test
    public void test_001_try_scrapper() throws IOException {
        final String symbol = "MSFT";
        final Stock stock = YahooFinance.get(symbol);
        System.out.println("stock = " + stock.getQuote().getPrice());
    }
}
