package aseca.roobinhood.api.service;

import aseca.roobinhood.api.domain.Ticker;
import aseca.roobinhood.api.domain.User;
import aseca.roobinhood.api.dto.StockInfoDto;
import aseca.roobinhood.api.dto.TickerDto;
import aseca.roobinhood.api.dto.TransactionDto;
import aseca.roobinhood.api.dto.TransactionResponseDto;
import aseca.roobinhood.api.exceptions.BadRequestException;
import aseca.roobinhood.api.exceptions.NotFoundException;
import aseca.roobinhood.api.repository.TickerRepository;
import aseca.roobinhood.api.repository.UserRepository;
import aseca.roobinhood.api.utils.UserMocking;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles("mem")
@SpringBootTest
class TransactionServiceTest {

    private final TransactionService transactionService;
    private final UserRepository userRepository;
    private final TickerRepository tickerRepository;

    @Autowired
    TransactionServiceTest(TransactionService transactionService, UserRepository userRepository, TickerRepository tickerRepository) {
        this.transactionService = transactionService;
        this.userRepository = userRepository;
        this.tickerRepository = tickerRepository;
    }

    @Test
    @Transactional
    @WithMockUser("test")
    public void test_001_buyStockSuccessfully() {
        userRepository.save(UserMocking.generateRawUser("test", 10000.0));
        Ticker ticker = tickerRepository.save(new Ticker("ASECA", "Aseg Calidad co"));
        double amount = 10;
        double price = 1000;
        TransactionDto transactionDto = new TransactionDto(TickerDto.from(ticker), amount, price);
        final TransactionResponseDto dto = transactionService.buyStock(transactionDto);
        assertThat(dto).isNotNull();
        assertThat(dto.getAmount()).isEqualTo(amount);
        assertThat(dto.getPrice()).isEqualTo(price);
        assertThat(dto.getTickerDto().getTickerName()).isEqualTo(ticker.getTickerName());
    }

    @Test
    @Transactional
    @WithMockUser("test")
    public void test_002_tryingToBuyStock_HasNoFounds() {
        userRepository.save(UserMocking.generateRawUser("test", 0));
        Ticker ticker = tickerRepository.save(new Ticker("ASECA", "Aseg Calidad co"));
        TransactionDto transactionDto = new TransactionDto(TickerDto.from(ticker), 10, 1000);
        Assert.assertThrows(BadRequestException.class, () -> transactionService.buyStock(transactionDto));
    }

    @Test
    @Transactional
    @WithMockUser("test")
    public void test_003_buyStockNotExists() {
        userRepository.save(UserMocking.generateRawUser("test"));
        Ticker ticker = new Ticker(0L, "ASECA", "Aseg Calidad co");
        TransactionDto transactionDto = new TransactionDto(TickerDto.from(ticker), 10, 1000);
        Assert.assertThrows(NotFoundException.class, () -> transactionService.buyStock(transactionDto));
    }

    @Test
    @WithMockUser("test")
    public void test_004_getEmptyStocksBought() {
        final User user = userRepository.save(UserMocking.generateRawUser("test"));
        final List<StockInfoDto> allStocksBought = transactionService.getAllStocksBoughtByUser(user);
        assertThat(allStocksBought).isEmpty();
    }
}