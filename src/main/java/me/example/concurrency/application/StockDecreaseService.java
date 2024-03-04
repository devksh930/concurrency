package me.example.concurrency.application;

import lombok.RequiredArgsConstructor;
import me.example.concurrency.domain.StockManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StockDecreaseService {

    private final StockManager stockManager;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void decrease(final Long stockId, final Long quantity) {
        stockManager.decrease(stockId, quantity);
    }

}
