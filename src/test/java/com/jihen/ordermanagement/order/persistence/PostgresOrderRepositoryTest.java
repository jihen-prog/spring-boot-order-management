package com.jihen.ordermanagement.order.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.jihen.ordermanagement.order.Order;
import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * Verifies repository persistence through Spring Data JPA and Flyway migrations.
 * Uses an isolated in-memory H2 database in PostgreSQL compatibility mode.
 */
@SpringBootTest
@Transactional
class PostgresOrderRepositoryTest {
    @Autowired
    private PostgresOrderRepository repository;

    /**
     * Saves an order and retrieves the same persisted values.
     */
    @Test
    void savesAndFindsOrder() {
        UUID id = UUID.randomUUID();
        Order order = new Order(id, "Monitor", 2, new BigDecimal("149.50"));

        repository.save(order);

        Order stored = repository.findById(id).orElseThrow();
        assertEquals("Monitor", stored.productName());
        assertEquals(2, stored.quantity());
        assertEquals(0, new BigDecimal("149.50").compareTo(stored.unitPrice()));
        assertTrue(repository.findAll().stream().anyMatch(item -> item.id().equals(id)));
    }
}
