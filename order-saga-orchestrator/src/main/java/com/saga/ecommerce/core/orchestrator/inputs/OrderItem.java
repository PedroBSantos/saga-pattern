package com.saga.ecommerce.core.orchestrator.inputs;

import java.util.UUID;

public class OrderItem {
    
    private UUID productId;
    private int amount;
    private double productUnitPrice;

    protected OrderItem() {
    }

    public OrderItem(UUID productId, int amount, double productUnitPrice) {
        this.productId = productId;
        this.amount = amount;
        this.productUnitPrice = productUnitPrice;
    }

    public UUID getProductId() {
        return productId;
    }

    public int getAmount() {
        return amount;
    }

    public double getProductUnitPrice() {
        return productUnitPrice;
    }
}
