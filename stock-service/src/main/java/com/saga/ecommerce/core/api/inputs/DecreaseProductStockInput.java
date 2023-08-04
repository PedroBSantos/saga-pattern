package com.saga.ecommerce.core.api.inputs;

import java.util.UUID;

public class DecreaseProductStockInput {
    
    private UUID productId;
    private int quantity;

    protected DecreaseProductStockInput() {
    }

    public DecreaseProductStockInput(UUID productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public UUID getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
