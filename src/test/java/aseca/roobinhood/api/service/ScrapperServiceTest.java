package aseca.roobinhood.api.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@ActiveProfiles("mem")
@SpringBootTest
public class ScrapperServiceTest {

    private final StockScrapperService stockScrapperService;

    @Autowired
    public ScrapperServiceTest(StockScrapperService stockScrapperService) {
        this.stockScrapperService = stockScrapperService;
    }

    @Test
    public void test_001_trySuccessScrapper() throws IOException {
        final String symbol = "MSFT";
        final double price = stockScrapperService.getPrice(symbol);
        assertTrue(price > 0);
    }

    @Test
    public void test_002_tryFailureScrapper() {
        final String symbol = "MSFTTT";
        Assertions.assertThatThrownBy(() ->
                stockScrapperService.getPrice(symbol));
    }

    @Test
    public void test_003_tryFailureScrapper() {
        Assertions.assertThatThrownBy(() ->
                stockScrapperService.getPrice(null));
    }
}
