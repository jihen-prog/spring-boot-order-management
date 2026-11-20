package com.jihen.ordermanagement.order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Defines persistence operations for orders independently of the storage technology.
 */
public interface OrderRepository {
    /**
     * Stores an order using its unique identifier.
     *
     * @param order order to persist
     * @return the stored order
     */
    Order save(Order order);
    /**
     * Looks up an order by identifier.
     *
     * @param id unique order identifier
     * @return the order if present, otherwise an empty optional
     */
    Optional<Order> findById(UUID id);
    /**
     * Retrieves all stored orders without a guaranteed ordering.
     *
     * @return a snapshot list of orders
     */
    List<Order> findAll();
}
