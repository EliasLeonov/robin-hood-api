package aseca.roobinhood.api.controller;

import aseca.roobinhood.api.dto.StockInfoDto;
import aseca.roobinhood.api.dto.TransactionDto;
import aseca.roobinhood.api.dto.TransactionResponseDto;
import aseca.roobinhood.api.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transaction")
    public TransactionResponseDto buy(@RequestBody @Valid TransactionDto transactionDto){
        return transactionService.buyStock(transactionDto);
    }

    @GetMapping("/get-all")
    public List<StockInfoDto> getAllByUser(){
        return transactionService.getAllStocks();
    }

}
