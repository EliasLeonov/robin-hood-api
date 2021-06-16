package aseca.roobinhood.api.controller;

import aseca.roobinhood.api.dto.CompanyDto;
import aseca.roobinhood.api.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping()
    public List<CompanyDto> getAllCompanies() {
        return companyService.getAllCompanies();
    }
}
