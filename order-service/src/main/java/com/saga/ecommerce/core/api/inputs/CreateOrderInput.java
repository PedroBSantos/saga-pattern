package com.saga.ecommerce.core.api.inputs;

import java.util.List;
import java.util.UUID;

public class CreateOrderInput {
    
    private UUID id;
    private UUID customerId;
    private List<CreateOrderItemInput> orderItems;
    private DeliveryAddressInput deliveryAddress;

    protected CreateOrderInput() {
    }

    public CreateOrderInput(UUID id, UUID customerId, List<CreateOrderItemInput> orderItems, DeliveryAddressInput deliveryAddress) {
        this.id = id;
        this.customerId = customerId;
        this.orderItems = orderItems;
        this.deliveryAddress = deliveryAddress;
    }

    public UUID getId() {
        return id;
    }

    public UUID getCustomerId() {
        return this.customerId;
    }

    public List<CreateOrderItemInput> getOrderItems() {
        return this.orderItems.stream().toList();
    }

    public DeliveryAddressInput getDeliveryAddress() {
        return deliveryAddress;
    }
}
