package me.example.concurrency.domain;

import java.util.List;
import java.util.Optional;

public interface StockRepository {

    Optional<Stock> findById(final Long id);

    Stock save(final Stock stock);

    List<Stock> saveAll(List<Stock> list);

    int decrease(
        final Long id,
        final Long quantity
    );
}
