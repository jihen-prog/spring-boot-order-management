package com.jihen.ordermanagement.order;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Immutable representation of an order for one product.
 *
 * @param id unique identifier assigned to the order
 * @param productName name of the ordered product
 * @param quantity number of units ordered
 * @param unitPrice price per unit, represented without floating-point rounding
 */
public record Order(UUID id, String productName, int quantity, BigDecimal unitPrice) {
    /**
     * Calculates the order total before any taxes or discounts.
     *
     * @return unit price multiplied by quantity
     */
    public BigDecimal total() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
