package aseca.roobinhood.api.service;

import aseca.roobinhood.api.domain.Transaction;
import aseca.roobinhood.api.domain.User;
import aseca.roobinhood.api.dto.StockDto;
import aseca.roobinhood.api.dto.TransactionDto;
import aseca.roobinhood.api.dto.TransactionResponseDto;
import aseca.roobinhood.api.factory.TransactionFactory;
import aseca.roobinhood.api.repository.TransactionRepository;
import aseca.roobinhood.api.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, UserService userService, SessionUtils sessionUtils, TransactionFactory transactionFactory) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
        this.sessionUtils = sessionUtils;
        this.transactionFactory = transactionFactory;
    }

    public TransactionResponseDto buyStock(TransactionDto dto){
        final Transaction transaction = transactionRepository.save(transactionFactory.convert(dto));
        userService.removeAmount(dto.getAmount() * dto.getPrice());
        return TransactionResponseDto.from(transaction);
    }

    public List<StockDto> getAllStocks() {
        final User user = sessionUtils.getTokenUserInformation();
        Map<String, StockDto> map = new HashMap<>();
        final List<Transaction> transactions = transactionRepository.findAllByUserId(user.getId());
        transactions.forEach(transaction -> {
            final StockDto stock = transactionFactory.getStockByTransaction(transaction);
            final String name = stock.getTickerName();
            if (map.containsKey(name))
                map.put(name, updateStocks(map.get(name), stock));
            else map.put(name, stock);
        });
        return new ArrayList<>(map.values());
    }

    private StockDto updateStocks(StockDto s1, StockDto s2) {
        s1.setAmount(s1.getAmount() + s2.getAmount());
        s1.setValue(s1.getValue() + s2.getValue());
        return s1;
    }


}
