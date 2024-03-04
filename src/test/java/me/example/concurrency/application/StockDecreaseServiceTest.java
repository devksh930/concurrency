package me.example.concurrency.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import me.example.concurrency.domain.Stock;
import me.example.concurrency.domain.StockRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StockDecreaseServiceTest {

    @Autowired
    private StockDecreaseService stockDecreaseService;
    @Autowired
    private StockRepository stockRepository;

    @Test
    void createItem() throws InterruptedException {
        final int treadCount = 1000;
        final AtomicInteger sum = new AtomicInteger();

        final ExecutorService executorService = Executors.newFixedThreadPool(64);
        final CountDownLatch countDownLatch = new CountDownLatch(treadCount);

        for (int i = 0; i < treadCount; i++) {
            executorService.submit(() -> {
                try {
                    int randomNum = ThreadLocalRandom.current().nextInt(1, 11);
                    stockDecreaseService.decrease(1L, Long.valueOf(randomNum));
                    sum.addAndGet(randomNum);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        final Stock stock = stockRepository.findById(1L).orElseThrow();

        assertEquals(10000000-sum.intValue(), stock.getQuantity());
    }
}
