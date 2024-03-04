package me.example.concurrency.application;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import me.example.concurrency.domain.Item;
import me.example.concurrency.domain.ItemManager;
import me.example.concurrency.domain.Stock;
import me.example.concurrency.domain.StockManager;
import me.example.concurrency.dto.request.ItemCreateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemCreateService {

    private final ItemMapper itemMapper;
    private final StockMapper stockMapper;
    private final StockManager stockManager;
    private final ItemManager itemManager;

    @Transactional
    public Long createItem(
        final ItemCreateRequest request
    ) {
        final Item item = itemManager.create(itemMapper.createRequestToEntity(request));
        final List<Stock> stocks = mapRequestToEntities(request);
        associateStocksWithItem(item, stocks);
        stockManager.createAll(stocks);
        return item.getId();
    }

    private List<Stock> mapRequestToEntities(
        final ItemCreateRequest request
    ) {
        return Stream.ofNullable(request.stocks())
            .flatMap(Collection::stream)
            .map(stockMapper::createRequestToEntity)
            .toList();
    }

    private void associateStocksWithItem(
        final Item item,
        final List<Stock> stocks
    ) {
        stocks.forEach(stock -> stock.with(item));
    }
}