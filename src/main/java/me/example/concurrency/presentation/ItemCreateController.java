package me.example.concurrency.presentation;


import java.net.URI;
import lombok.RequiredArgsConstructor;
import me.example.concurrency.application.ItemCreateService;
import me.example.concurrency.dto.request.ItemCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemCreateController {

    private final ItemCreateService itemCreateService;

    @PostMapping
    public ResponseEntity<Void> createItem(final ItemCreateRequest request) {
        final Long save = itemCreateService.createItem(request);

        return ResponseEntity.created(URI.create("/items/" + save))
            .build();
    }
}
