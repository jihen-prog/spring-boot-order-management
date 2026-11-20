package com.jihen.ordermanagement.order;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class OrderServiceTest {
    private final OrderService service = new OrderService(new InMemoryOrderRepository());

    @Test
    void createsAndRetrievesOrder() {
        Order order = service.create(new CreateOrderRequest("Keyboard", 2, new BigDecimal("49.99")));
        assertEquals(new BigDecimal("99.98"), order.total());
        assertEquals(order, service.findById(order.id()).orElseThrow());
        assertEquals(1, service.findAll().size());
    }

    @Test
    void rejectsInvalidOrder() {
        assertThrows(IllegalArgumentException.class,
                () -> service.create(new CreateOrderRequest("", 0, BigDecimal.ONE)));
    }
}
