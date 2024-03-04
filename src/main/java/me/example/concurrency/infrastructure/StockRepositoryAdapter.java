package me.example.concurrency.infrastructure;

import java.util.List;
import java.util.Optional;
import me.example.concurrency.domain.Stock;
import me.example.concurrency.domain.StockRepository;
import org.springframework.stereotype.Repository;

@Repository
public class StockRepositoryAdapter implements StockRepository {

    private final StockJpaRepository stockJpaRepository;

    public StockRepositoryAdapter(StockJpaRepository stockJpaRepository) {
        this.stockJpaRepository = stockJpaRepository;
    }

    @Override
    public Optional<Stock> findById(
        final Long id
    ) {
        return stockJpaRepository.findById(id);
    }

    @Override
    public Stock save(
        final Stock stock
    ) {
        return stockJpaRepository.save(stock);
    }

    @Override
    public List<Stock> saveAll(
        final List<Stock> list
    ) {
        return stockJpaRepository.saveAll(list);
    }

    @Override
    public int decrease(
        final Long id,
        final Long quantity
    ) {
        return stockJpaRepository.decrease(id, quantity);
    }
}
