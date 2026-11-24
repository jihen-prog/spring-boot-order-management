package com.jihen.ordermanagement.order.persistence;

import com.jihen.ordermanagement.order.Order;
import com.jihen.ordermanagement.order.OrderRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

/**
 * Database-backed implementation of the domain repository abstraction.
 * Converts between immutable domain records and mutable JPA entities.
 */
@Repository
public class PostgresOrderRepository implements OrderRepository {
    private final JpaOrderRepository jpaRepository;

    /**
     * Creates the repository adapter.
     *
     * @param jpaRepository Spring Data repository used for database operations
     */
    public PostgresOrderRepository(JpaOrderRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    /**
     * Persists an order to the database.
     *
     * @param order order to save
     * @return persisted order
     */
    @Override
    public Order save(Order order) {
        OrderEntity entity = new OrderEntity(order.id(), order.productName(),
                order.quantity(), order.unitPrice());
        return toDomain(jpaRepository.save(entity));
    }

    /**
     * Looks up a persisted order by identifier.
     *
     * @param id order identifier
     * @return matching order if found
     */
    @Override
    public Optional<Order> findById(UUID id) {
        return jpaRepository.findById(id).map(PostgresOrderRepository::toDomain);
    }

    /**
     * Lists all persisted orders.
     *
     * @return orders in unspecified order
     */
    @Override
    public List<Order> findAll() {
        return jpaRepository.findAll().stream().map(PostgresOrderRepository::toDomain).toList();
    }

    /**
     * Maps a JPA entity into the domain representation.
     *
     * @param entity stored entity
     * @return immutable domain order
     */
    private static Order toDomain(OrderEntity entity) {
        return new Order(entity.getId(), entity.getProductName(),
                entity.getQuantity(), entity.getUnitPrice());
    }
}
