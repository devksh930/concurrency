package me.example.concurrency.dto.request;

public record StockCreateRequest(
    String optionName,
    Long quantity
) {

}
