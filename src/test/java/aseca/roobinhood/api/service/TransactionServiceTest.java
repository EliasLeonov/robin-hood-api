package aseca.roobinhood.api.service;

import aseca.roobinhood.api.domain.User;
import aseca.roobinhood.api.dto.StockInfoDto;
import aseca.roobinhood.api.repository.UserRepository;
import aseca.roobinhood.api.utils.UserMocking;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@ActiveProfiles("mem")
@SpringBootTest
class TransactionServiceTest {

    private final TransactionService transactionService;
    private final UserRepository userRepository;

    @Autowired
    TransactionServiceTest(TransactionService transactionService, UserRepository userRepository) {
        this.transactionService = transactionService;
        this.userRepository = userRepository;
    }

    @Test
    @Transactional
    @WithMockUser("test")
    public void test_001_buyStockSuccessfully() {
        final User user = userRepository.save(UserMocking.generateRawUser("test"));
//        transactionService.buyStock()

    }

    @Test
    @WithMockUser("test")
    public void test_002_buyStockNotExists() {
        final User user = userRepository.save(UserMocking.generateRawUser("test"));

    }

    @Test
    @WithMockUser("test")
    public void test_002_getEmptyStocksBought() {
        final User user = userRepository.save(UserMocking.generateRawUser("test"));
        final List<StockInfoDto> allStocksBought = transactionService.getAllStocksBoughtByUser(user);
        Assertions.assertThat(allStocksBought).isEmpty();
    }
}