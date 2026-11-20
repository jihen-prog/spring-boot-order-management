package com.jihen.ordermanagement.order;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for order validation, creation, and retrieval behavior.
 */
class OrderServiceTest {
    private final OrderService service = new OrderService(new InMemoryOrderRepository());

    @Test
    /**
     * Verifies that a created order can be retrieved and its total is calculated.
     */
    void createsAndRetrievesOrder() {
        Order order = service.create(new CreateOrderRequest("Keyboard", 2, new BigDecimal("49.99")));
        assertEquals(new BigDecimal("99.98"), order.total());
        assertEquals(order, service.findById(order.id()).orElseThrow());
        assertEquals(1, service.findAll().size());
    }

    @Test
    /**
     * Verifies that an invalid order request is rejected.
     */
    void rejectsInvalidOrder() {
        assertThrows(IllegalArgumentException.class,
                () -> service.create(new CreateOrderRequest("", 0, BigDecimal.ONE)));
    }
}
