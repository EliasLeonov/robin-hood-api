package aseca.roobinhood.api.controller;

import aseca.roobinhood.api.domain.Ticker;
import aseca.roobinhood.api.dto.CompanyDto;
import aseca.roobinhood.api.service.CompanyService;
import aseca.roobinhood.api.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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

    }

    @Test
    public void test_002_create_company() throws IOException {
        final List<CompanyDto> allCompanies = companyService.getAllCompanies();
        allCompanies.forEach(System.out::println);
    }

    @Test
    public void test_004_create_company() throws IOException {
        final List<String> allCompanies = companyService.SP500Companies().stream().map(Ticker::getTickerName).collect(Collectors.toList());
        allCompanies.forEach(System.out::println);
    }

}