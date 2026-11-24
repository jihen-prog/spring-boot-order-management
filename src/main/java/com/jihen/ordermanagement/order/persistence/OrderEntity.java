package com.jihen.ordermanagement.order.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * JPA representation of an order persisted in the orders table.
 */
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    private UUID id;

    @Column(name = "product_name", nullable = false, length = 255)
    private String productName;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "unit_price", nullable = false, precision = 19, scale = 2)
    private BigDecimal unitPrice;

    /** Required by JPA for entity materialization. */
    protected OrderEntity() {}

    /**
     * Constructs an entity from validated order values.
     *
     * @param id unique order identifier
     * @param productName product name
     * @param quantity number of units
     * @param unitPrice price per unit
     */
    public OrderEntity(UUID id, String productName, int quantity, BigDecimal unitPrice) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    /** @return unique identifier */
    public UUID getId() { return id; }

    /** @return product name */
    public String getProductName() { return productName; }

    /** @return number of units */
    public int getQuantity() { return quantity; }

    /** @return price per unit */
    public BigDecimal getUnitPrice() { return unitPrice; }
}
