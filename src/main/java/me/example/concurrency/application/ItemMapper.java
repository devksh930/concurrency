package me.example.concurrency.application;

import me.example.concurrency.domain.Item;
import me.example.concurrency.dto.request.ItemCreateRequest;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public Item createRequestToEntity(ItemCreateRequest request) {
        return Item.builder()
            .name(request.name())
            .build();
    }

}
