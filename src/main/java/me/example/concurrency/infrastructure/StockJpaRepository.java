package me.example.concurrency.infrastructure;

import me.example.concurrency.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface StockJpaRepository extends JpaRepository<Stock, Long> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("""
        UPDATE Stock s 
        SET s.quantity=s.quantity-:quantity 
        WHERE s.id=:id 
        AND s.quantity>=:quantity
        """)
    int decrease(
        final Long id,
        final Long quantity
    );
}
