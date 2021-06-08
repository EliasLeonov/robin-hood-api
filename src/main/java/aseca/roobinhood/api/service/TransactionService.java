package aseca.roobinhood.api.service;

import aseca.roobinhood.api.domain.Transaction;
import aseca.roobinhood.api.domain.User;
import aseca.roobinhood.api.dto.StockDto;
import aseca.roobinhood.api.dto.TransactionDto;
import aseca.roobinhood.api.dto.TransactionResponseDto;
import aseca.roobinhood.api.factory.TransactionFactory;
import aseca.roobinhood.api.repository.TickerRepository;
import aseca.roobinhood.api.repository.TransactionRepository;
import aseca.roobinhood.api.repository.UserRepository;
import aseca.roobinhood.api.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TickerRepository tickerRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final SessionUtils sessionUtils;
    private final TransactionFactory transactionFactory;

    @Autowired
    public TransactionService(TransactionRepository repository, TickerRepository tickerRepository, UserRepository userRepository, UserService userService, SessionUtils sessionUtils, TransactionFactory transactionFactory) {
        this.transactionRepository = repository;
        this.tickerRepository = tickerRepository;
        this.userRepository = userRepository;
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
        final List<Transaction> transactions = transactionRepository.findAllByUserId(user.getId());
        List<StockDto> stocks = transactions.stream().map(transactionFactory::getStockByTransaction).collect(Collectors.toList());
        final List<String> tickerNames = transactions.stream().map(t -> t.getTicker().getTickerName()).collect(Collectors.toList());
        return new ArrayList<>();
    }

}
