package aseca.roobinhood.api.repository;

import aseca.roobinhood.api.domain.Stoncks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stoncks, Long> {
}
