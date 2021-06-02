package aseca.roobinhood.api.controller;

import aseca.roobinhood.api.dto.CompanyDto;
import aseca.roobinhood.api.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService service;

    @Autowired
    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public CompanyDto getCompanyById(@PathVariable(value = "id") @Valid Long id){
        return service.getCompanyById(id);
    }
}
