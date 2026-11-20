package com.jihen.ordermanagement.order;

import java.math.BigDecimal;

public record CreateOrderRequest(String productName, int quantity, BigDecimal unitPrice) {}
