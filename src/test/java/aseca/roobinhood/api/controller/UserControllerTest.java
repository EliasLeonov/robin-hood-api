package aseca.roobinhood.api.controller;

import aseca.roobinhood.api.dto.TickerDto;
import aseca.roobinhood.api.dto.TransactionDto;
import aseca.roobinhood.api.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("mem")
@SpringBootTest
class UserControllerTest {

    private final TransactionService transactionService;

    @Autowired
    UserControllerTest(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Test
    public void test_001_create_transaction(){
        TickerDto tickerDto = TickerDto
                .builder()
                .id(1L)
                .companyName("greencode")
                .build();
        TransactionDto transactionDto = TransactionDto
                                                .builder()
                                                .tickerDto(tickerDto)
                                                .userId(1L)
                                                .amount(4L)
                                                .price(100L)
                                                .build();
        transactionService.buyStock(transactionDto);


    }

}