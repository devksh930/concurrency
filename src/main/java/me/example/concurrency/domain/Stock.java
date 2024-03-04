package me.example.concurrency.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String optionName;

    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    public void with(final Item item) {
        this.item = item;
    }

    @Builder
    public Stock(
        final Long id,
        final String optionName,
        final Long quantity,
        final Item item
    ) {
        this.id = id;
        this.optionName = optionName;
        this.quantity = quantity;
        this.item = item;
    }
}
