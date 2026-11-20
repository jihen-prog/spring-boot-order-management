package com.jihen.ordermanagement.order;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
/**
 * Coordinates order creation, validation, and retrieval.
 * Depends on the {@link OrderRepository} abstraction rather than a storage implementation.
 */
public class OrderService {
    private final OrderRepository repository;

    /**
     * Creates the service with its order repository dependency.
     *
     * @param repository repository used to store and retrieve orders
     */
    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    /**
     * Validates an order request, assigns a new identifier, and saves the order.
     *
     * @param request product, quantity, and unit price to order
     * @return the newly created order
     * @throws IllegalArgumentException if the request is null, the product name is blank,
     *         the quantity is not positive, or the unit price is null or negative
     */
    public Order create(CreateOrderRequest request) {
        if (request == null || request.productName() == null || request.productName().isBlank()
                || request.quantity() <= 0 || request.unitPrice() == null
                || request.unitPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Product name, positive quantity and non-negative price are required");
        }
        return repository.save(new Order(UUID.randomUUID(), request.productName().trim(),
                request.quantity(), request.unitPrice()));
    }

    /**
     * Retrieves an order by its identifier.
     *
     * @param id identifier of the requested order
     * @return an optional containing the matching order, if any
     */
    public Optional<Order> findById(UUID id) {
        return repository.findById(id);
    }

    /**
     * Retrieves all orders from the repository.
     *
     * @return a list of currently stored orders
     */
    public List<Order> findAll() {
        return repository.findAll();
    }
}
