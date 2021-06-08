package aseca.roobinhood.api.factory;

import aseca.roobinhood.api.domain.Ticker;
import aseca.roobinhood.api.domain.Transaction;
import aseca.roobinhood.api.domain.User;
import aseca.roobinhood.api.dto.StockDto;
import aseca.roobinhood.api.dto.TransactionDto;
import aseca.roobinhood.api.exceptions.NotFoundException;
import aseca.roobinhood.api.repository.TickerRepository;
import aseca.roobinhood.api.repository.TransactionRepository;
import aseca.roobinhood.api.repository.UserRepository;
import aseca.roobinhood.api.utils.SessionUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TransactionFactory {

    private final TransactionRepository repository;
    private final TickerRepository tickerRepository;
    private final UserRepository userRepository;
    private final SessionUtils sessionUtils;
    private final TransactionFactory transactionFactory;

    public TransactionFactory(TransactionRepository repository, TickerRepository tickerRepository, UserRepository userRepository, SessionUtils sessionUtils, TransactionFactory transactionFactory) {
        this.repository = repository;
        this.tickerRepository = tickerRepository;
        this.userRepository = userRepository;
        this.sessionUtils = sessionUtils;
        this.transactionFactory = transactionFactory;
    }

    public Transaction convert(TransactionDto dto) {
        final User user = sessionUtils.getTokenUserInformation();
        final Ticker ticker = tickerRepository.findById(dto.getTickerDto().getId()).orElseThrow(() -> new NotFoundException("Ticker does not found"));
        return new Transaction(ticker, user, dto.getPrice(), dto.getAmount(), LocalDateTime.now());
    }

    public StockDto getStockByTransaction(Transaction transaction) {
        final Ticker ticker = transaction.getTicker();
        return new StockDto(transaction.getPrice(), transaction.getAmount(), ticker.getTickerName(), ticker.getCompanyName());
    }

}
