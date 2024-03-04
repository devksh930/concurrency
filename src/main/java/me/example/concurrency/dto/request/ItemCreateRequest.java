package me.example.concurrency.dto.request;

import java.util.List;

public record ItemCreateRequest(
    String name,
    List<StockCreateRequest> stocks
) {

}
