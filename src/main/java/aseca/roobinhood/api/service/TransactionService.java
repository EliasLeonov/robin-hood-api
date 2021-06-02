package aseca.roobinhood.api.service;

import aseca.roobinhood.api.domain.Ticker;
import aseca.roobinhood.api.domain.Transaction;
import aseca.roobinhood.api.domain.User;
import aseca.roobinhood.api.dto.TransactionDto;
import aseca.roobinhood.api.dto.TransactionResponseDto;
import aseca.roobinhood.api.exceptions.NotFoundException;
import aseca.roobinhood.api.repository.TickerRepository;
import aseca.roobinhood.api.repository.TransactionRepository;
import aseca.roobinhood.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {
    private final TransactionRepository repository;
    private final TickerRepository tickerRepository;
    private final UserRepository userRepository;

    @Autowired
    public TransactionService(TransactionRepository repository, TickerRepository tickerRepository, UserRepository userRepository) {
        this.repository = repository;
        this.tickerRepository = tickerRepository;
        this.userRepository = userRepository;
    }

    public TransactionResponseDto buyStock(TransactionDto transactionDto){
        Ticker ticker = tickerRepository.findById(transactionDto.getTickerDto().getId()).orElseThrow(() -> new NotFoundException("Ticker does not found"));
        User user = userRepository.findById(transactionDto.getUserId()).orElseThrow(() -> new NotFoundException("User does not found"));
        Transaction transaction = repository.save(Transaction
                                                    .builder()
                                                    .ticker(ticker)
                                                    .user(user)
                                                    .price(transactionDto.getPrice())
                                                    .amount(transactionDto.getAmount())
                                                    .dateTime(LocalDateTime.now())
                                                    .build());
        tickerRepository.save(ticker);
        return TransactionResponseDto.from(transaction);
    }
}
