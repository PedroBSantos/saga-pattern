package com.saga.ecommerce.core.api.inputs;

import java.util.UUID;

public class CreateOrderItemInput {
    
    private UUID productId;
    private int amount;
    private double productUnitPrice;

    protected CreateOrderItemInput() {
    }

    public CreateOrderItemInput(UUID productId, int amount, double productUnitPrice) {
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
