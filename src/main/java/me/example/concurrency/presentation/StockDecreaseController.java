package me.example.concurrency.presentation;

import lombok.RequiredArgsConstructor;
import me.example.concurrency.application.StockDecreaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stocks/{stockId}")
@RequiredArgsConstructor
public class StockDecreaseController {

    private final StockDecreaseService stockDecreaseService;

    @PatchMapping("/decrease")
    public ResponseEntity<Void> decreaseStock(
        @PathVariable final Long stockId,
        @RequestParam(name = "quantity") final Long quantity
    ) {
        stockDecreaseService.decrease(stockId, quantity);
        return ResponseEntity.noContent().build();
    }
}
