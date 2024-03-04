package me.example.concurrency.domain;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockManager {

    private final StockRepository stockRepository;

    public Stock create(final Stock stock) {
        return stockRepository.save(stock);
    }

    public List<Stock> createAll(final List<Stock> list) {
        return stockRepository.saveAll(list);
    }

    public void decrease(
        final Long id,
        final Long quantity
    ) {

        final int decrease = stockRepository.decrease(id, quantity);

        if (decrease != 1) {
            throw new RuntimeException("재고가 없거나 잘못된 값입니다");
        }
    }
}
