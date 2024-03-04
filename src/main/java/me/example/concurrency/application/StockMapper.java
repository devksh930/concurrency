package me.example.concurrency.application;

import me.example.concurrency.domain.Stock;
import me.example.concurrency.dto.request.StockCreateRequest;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {

    public Stock createRequestToEntity(
        final StockCreateRequest request
    ) {
        return Stock.builder()
            .optionName(request.optionName())
            .quantity(request.quantity())
            .build();
    }

}
