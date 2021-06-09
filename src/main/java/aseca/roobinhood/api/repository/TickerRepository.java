package aseca.roobinhood.api.repository;

import aseca.roobinhood.api.domain.Ticker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TickerRepository extends JpaRepository<Ticker, Long> {
    Optional<Ticker> findByTickerName(String tickerName);
}
