package com.jihen.ordermanagement.order;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order create(CreateOrderRequest request) {
        if (request == null || request.productName() == null || request.productName().isBlank()
                || request.quantity() <= 0 || request.unitPrice() == null
                || request.unitPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Product name, positive quantity and non-negative price are required");
        }
        return repository.save(new Order(UUID.randomUUID(), request.productName().trim(),
                request.quantity(), request.unitPrice()));
    }

    public Optional<Order> findById(UUID id) {
        return repository.findById(id);
    }

    public List<Order> findAll() {
        return repository.findAll();
    }
}
