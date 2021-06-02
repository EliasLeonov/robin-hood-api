package aseca.roobinhood.api.service;

import aseca.roobinhood.api.dto.CompanyDto;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CompanyService {
    Set<CompanyDto> companies;

    public CompanyDto getCompanyById(Long id){
        return CompanyDto.builder().build();
    }
}
