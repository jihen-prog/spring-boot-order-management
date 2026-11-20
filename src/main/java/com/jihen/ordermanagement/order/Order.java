package com.jihen.ordermanagement.order;

import java.math.BigDecimal;
import java.util.UUID;

public record Order(UUID id, String productName, int quantity, BigDecimal unitPrice) {
    public BigDecimal total() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
