package com.jihen.ordermanagement.order.persistence;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data repository for persisted order entities.
 */
public interface JpaOrderRepository extends JpaRepository<OrderEntity, UUID> {
}
