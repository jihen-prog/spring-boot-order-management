package com.jihen.ordermanagement.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Thread-safe repository for isolated unit tests and local experiments.
 * This implementation is not registered as a Spring bean; production uses PostgreSQL.
 */
public class InMemoryOrderRepository implements OrderRepository {
    private final Map<UUID, Order> orders = new ConcurrentHashMap<>();

    /**
     * Stores an order in memory.
     *
     * @param order order to store
     * @return stored order
     */
    @Override
    public Order save(Order order) {
        orders.put(order.id(), order);
        return order;
    }

    /**
     * Finds an order by identifier.
     *
     * @param id order identifier
     * @return matching order if present
     */
    @Override
    public Optional<Order> findById(UUID id) {
        return Optional.ofNullable(orders.get(id));
    }

    /**
     * Returns a snapshot of stored orders.
     *
     * @return all orders in unspecified order
     */
    @Override
    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }
}
