package com.saga.ecommerce.core.services.stock.models;

import java.util.UUID;

public class DecreaseProductStock {

    private UUID productId;
    private int quantity;

    public DecreaseProductStock(UUID productId, int quantity) {
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
