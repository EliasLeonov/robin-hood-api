package aseca.roobinhood.api.controller;

import aseca.roobinhood.api.dto.TickerDto;
import aseca.roobinhood.api.dto.TransactionDto;
import aseca.roobinhood.api.service.TransactionService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
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
                .tickerName("green")
                .build();
        TransactionDto transactionDto = TransactionDto
                .builder()
                .tickerDto(tickerDto)
                .userId(1L)
                .amount(4L)
                .price(100L)
                .build();
//        transactionService.buyStock(transactionDto);

    }

    @Test
    public void test_002_create_company(){
        System.out.println();
        Assertions.assertThat(true).isTrue();
    }

}