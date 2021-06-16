package aseca.roobinhood.api.service;

import aseca.roobinhood.api.dto.CompanyDto;
import aseca.roobinhood.api.exceptions.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@ActiveProfiles("mem")
@SpringBootTest
class CompanyServiceTest {

    private final CompanyService companyService;

    @Autowired
    CompanyServiceTest(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Test
    public void test001GetAllCompanies() throws IOException {
        List<CompanyDto> companies = companyService.getAllCompanies();
        assertTrue(companies.size() > 0);
    }

    @Test
    public void test002GetAllCompanies() throws IOException {
        List<CompanyDto> companies = companyService.getAllCompanies();
        CompanyDto companyDto = companies.stream().findFirst().orElseThrow(() -> new NotFoundException("Companies list is empty"));
        assertFalse(companyDto.getName().isEmpty());
        assertFalse(companyDto.getTicker().isEmpty());
    }
}