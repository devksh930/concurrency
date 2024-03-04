package me.example.concurrency.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemManager {

    private final ItemRepository itemRepository;

    public Item create(Item item) {
        return itemRepository.save(item);
    }
}
