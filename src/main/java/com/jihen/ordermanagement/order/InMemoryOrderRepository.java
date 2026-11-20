package com.jihen.ordermanagement.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
/**
 * Thread-safe, in-memory implementation of {@link OrderRepository}.
 * Orders are lost when the application process restarts.
 */
public class InMemoryOrderRepository implements OrderRepository {
    private final Map<UUID, Order> orders = new ConcurrentHashMap<>();

    @Override
    /**
     * Inserts or replaces an order in the in-memory store.
     *
     * @param order order to store
     * @return the stored order
     */
    public Order save(Order order) {
        orders.put(order.id(), order);
        return order;
    }

    @Override
    /**
     * Finds an order in the in-memory store.
     *
     * @param id order identifier
     * @return an optional containing the order when found
     */
    public Optional<Order> findById(UUID id) {
        return Optional.ofNullable(orders.get(id));
    }

    @Override
    /**
     * Copies the currently stored orders into a new list.
     *
     * @return a snapshot of all orders in unspecified order
     */
    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }
}
