package com.jihen.ordermanagement.order;

import java.math.BigDecimal;

/**
 * Request payload used when creating a new order.
 * Validation is currently performed by {@link OrderService}.
 *
 * @param productName nonblank product name
 * @param quantity positive number of units to order
 * @param unitPrice nonnegative price per unit
 */
public record CreateOrderRequest(String productName, int quantity, BigDecimal unitPrice) {}
