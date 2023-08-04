package com.saga.ecommerce.core.orchestrator.step_error_observers.stock;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.saga.ecommerce.core.orchestrator.inputs.OrderItem;

public class FailedOrderProcessed {
    
    private UUID orderId;
    private List<OrderItem> orderItemsProcessedUntilFailure;

    protected FailedOrderProcessed() {
        this.orderItemsProcessedUntilFailure = new ArrayList<>();
    }

    public FailedOrderProcessed(UUID orderId, List<OrderItem> orderItemsProcessedUntilFailure) {
        this.orderId = orderId;
        this.orderItemsProcessedUntilFailure = orderItemsProcessedUntilFailure;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItemsProcessedUntilFailure;
    }
}
