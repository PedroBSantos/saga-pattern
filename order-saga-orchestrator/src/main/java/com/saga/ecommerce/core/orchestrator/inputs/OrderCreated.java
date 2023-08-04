package com.saga.ecommerce.core.orchestrator.inputs;

import java.util.List;
import java.util.UUID;

public class OrderCreated {
    
    private UUID id;
    private UUID customerId;
    private List<OrderItem> orderItems;
    private DeliveryAddress deliveryAddress;

    protected OrderCreated() {
    }

    public OrderCreated(UUID id, UUID customerId, List<OrderItem> orderItems,
            DeliveryAddress deliveryAddress) {
        this.id = id;
        this.customerId = customerId;
        this.orderItems = orderItems;
        this.deliveryAddress = deliveryAddress;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public String getCepToDelivery() {
        return this.deliveryAddress.getCep();
    }

    public String getNumberToDelivery() {
        return this.deliveryAddress.getNumber();
    }

    public DeliveryAddress getDeliveryAddress() {
        return this.deliveryAddress;
    }

    public UUID getId() {
        return id;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
}
