package aseca.roobinhood.api.controller;

import aseca.roobinhood.api.dto.TransactionDto;
import aseca.roobinhood.api.dto.TransactionResponseDto;
import aseca.roobinhood.api.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService service;

    @Autowired
    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping
    public TransactionResponseDto buy(@RequestBody @Valid TransactionDto transactionDto){
        return service.buyStock(transactionDto);
    }

}
