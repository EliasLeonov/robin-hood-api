package aseca.roobinhood.api.controller;

import aseca.roobinhood.api.dto.StockDto;
import aseca.roobinhood.api.dto.TransactionDto;
import aseca.roobinhood.api.dto.TransactionResponseDto;
import aseca.roobinhood.api.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public TransactionResponseDto buy(@RequestBody @Valid TransactionDto transactionDto){
        return transactionService.buyStock(transactionDto);
    }

    @GetMapping
    public List<StockDto> getAllByUser(){
        return transactionService.getAllStocks();
    }

}
