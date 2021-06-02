package aseca.roobinhood.api.controller;

import aseca.roobinhood.api.dto.CompanyDto;
import aseca.roobinhood.api.dto.TickerDto;
import aseca.roobinhood.api.dto.TransactionDto;
import aseca.roobinhood.api.service.CompanyService;
import aseca.roobinhood.api.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ActiveProfiles("mem")
@SpringBootTest
class UserControllerTest {

    private final TransactionService transactionService;
    private final CompanyService companyService;

    @Autowired
    UserControllerTest(TransactionService transactionService, CompanyService companyService) {
        this.transactionService = transactionService;
        this.companyService = companyService;
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
        final List<CompanyDto> allCompanies = companyService.getAllCompanies();
        allCompanies.forEach(System.out::println);
    }

}