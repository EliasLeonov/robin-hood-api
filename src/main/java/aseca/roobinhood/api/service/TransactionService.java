package aseca.roobinhood.api.service;

import aseca.roobinhood.api.domain.Transaction;
import aseca.roobinhood.api.domain.User;
import aseca.roobinhood.api.dto.StockInfoDto;
import aseca.roobinhood.api.dto.TransactionDto;
import aseca.roobinhood.api.dto.TransactionResponseDto;
import aseca.roobinhood.api.factory.TransactionFactory;
import aseca.roobinhood.api.repository.TransactionRepository;
import aseca.roobinhood.api.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserService userService;
    private final SessionUtils sessionUtils;
    private final TransactionFactory transactionFactory;
    private final StockScrapperService stockScrapperService;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, UserService userService, SessionUtils sessionUtils, TransactionFactory transactionFactory, StockScrapperService stockScrapperService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
        this.sessionUtils = sessionUtils;
        this.transactionFactory = transactionFactory;
        this.stockScrapperService = stockScrapperService;
    }

    public TransactionResponseDto buyStock(TransactionDto dto) {
        final Transaction transaction = transactionRepository.save(transactionFactory.convert(dto));
        userService.removeAmount(dto.getAmount() * dto.getPrice());
        return TransactionResponseDto.from(transaction);
    }

    public List<StockInfoDto> getAllStocks() {
        final User user = sessionUtils.getTokenUserInformation();
        Map<String, StockInfoDto> stockInfoMap = new HashMap<>();
        final List<Transaction> transactions = transactionRepository.findAllByUserId(user.getId());
        transactions.forEach(transaction -> {
            final StockInfoDto stock = transactionFactory.getStockByTransaction(transaction);
            final String name = stock.getTickerName();
            if (stockInfoMap.containsKey(name))
                stockInfoMap.put(name, updateStocks(stockInfoMap.get(name), stock));
            else stockInfoMap.put(name, stock);
        });
        stockInfoMap.forEach((k, v) -> {
            try {
                v.setActualPrice(stockScrapperService.getStock(k).getQuote().getPrice().doubleValue());
                v.setResult((v.getActualPrice() / v.getPurchasePrice() - 1) * 100);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return new ArrayList<>(stockInfoMap.values());
    }

    private StockInfoDto updateStocks(StockInfoDto s1, StockInfoDto s2) {
        s1.setAmount(s1.getAmount() + s2.getAmount());
        s1.setActualTotal(s1.getActualTotal() + s2.getActualTotal());
        s1.setResult(s1.getAmount() * s1.getActualTotal());
        return s1;
    }


}
