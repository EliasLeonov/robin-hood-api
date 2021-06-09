package aseca.roobinhood.api.factory;

import aseca.roobinhood.api.domain.Ticker;
import aseca.roobinhood.api.domain.Transaction;
import aseca.roobinhood.api.domain.User;
import aseca.roobinhood.api.dto.StockDto;
import aseca.roobinhood.api.dto.TransactionDto;
import aseca.roobinhood.api.exceptions.NotFoundException;
import aseca.roobinhood.api.repository.TickerRepository;
import aseca.roobinhood.api.utils.SessionUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TransactionFactory {

    private final TickerRepository tickerRepository;
    private final SessionUtils sessionUtils;

    public TransactionFactory(TickerRepository tickerRepository, SessionUtils sessionUtils) {
        this.tickerRepository = tickerRepository;
        this.sessionUtils = sessionUtils;
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
