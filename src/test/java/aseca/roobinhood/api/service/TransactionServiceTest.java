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

    @Test
    @Transactional
    @WithMockUser("test2")
    public void test_005_getAllStocksBought(){
        userRepository.save(UserMocking.generateRawUser("test2", 10000.0));
        Ticker ticker = tickerRepository.save(new Ticker("AAPL", "Aseg Calidad co2"));
        double amount = 10;
        double price = 1000;
        TransactionDto transactionDto = new TransactionDto(TickerDto.from(ticker), amount, price);
        transactionService.buyStock(transactionDto);
        final List<StockInfoDto> allStockBought = transactionService.getAllStocksBought();
        assertThat(allStockBought.size()).isEqualTo(1);
    }

    @Test
    @Transactional
    @WithMockUser("test3")
    public void test_006_buyStockWithoutMoney(){
        userRepository.save(UserMocking.generateRawUser("test3", 0.0));
        Ticker ticker = tickerRepository.save(new Ticker("AAPL", "Aseg Calidad co"));
        double amount = 10;
        double price = 1000;
        TransactionDto transactionDto = new TransactionDto(TickerDto.from(ticker), amount, price);
        Assert.assertThrows(BadRequestException.class, () -> transactionService.buyStock(transactionDto));

    }

    @Test
    @Transactional
    @WithMockUser("test")
    public void test_007_checkUserBalance(){
        Double balance = 10000000.0;
        userRepository.save(UserMocking.generateRawUser("test", balance));
        Ticker ticker = tickerRepository.save(new Ticker("AAPL", "Aseg Calidad co"));
        double amount = 10;
        double price = 10000;
        TransactionDto transactionDto = new TransactionDto(TickerDto.from(ticker), amount, price);
        transactionService.buyStock(transactionDto);
        User user = userRepository.findByUsername("test").orElseThrow(() -> new NotFoundException("User does not found"));
        assertThat(user.getAccountBalance()).isEqualTo(balance - (amount * price));
    }

    @Test
    @Transactional
    @WithMockUser("aseca")
    public void test_008_buyMultipleStocks() {

        userRepository.save(UserMocking.generateRawUser("aseca", 10000000.0));

        Ticker tickerApple = tickerRepository.save(new Ticker("AAPL", "Apple"));
        double amount = 10;
        double price = 10000;
        TransactionDto transactionApplDto = new TransactionDto(TickerDto.from(tickerApple), amount, price);
        transactionService.buyStock(transactionApplDto);

        Ticker tickerTesla = tickerRepository.save(new Ticker("TSLA", "Tesla"));
        TransactionDto transactionTslaDto = new TransactionDto(TickerDto.from(tickerTesla), amount, price);
        transactionService.buyStock(transactionTslaDto);

        final List<StockInfoDto> allStockBought = transactionService.getAllStocksBought();
        assertThat(allStockBought.size()).isEqualTo(2);

    }

//    @Test
//    @Transactional
//    @WithMockUser("test")
//    public void test_009_
}