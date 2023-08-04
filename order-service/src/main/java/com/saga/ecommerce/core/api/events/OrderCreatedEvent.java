package com.saga.ecommerce.core.api.events;

import java.util.List;
import java.util.UUID;

import com.saga.ecommerce.core.api.inputs.*;

public class OrderCreatedEvent {
    
    private UUID id;
    private UUID customerId;
    private List<CreateOrderItemInput> orderItems;
    private DeliveryAddressInput deliveryAddress;

    public OrderCreatedEvent(UUID id, UUID customerId, List<CreateOrderItemInput> orderItems,
            DeliveryAddressInput deliveryAddress) {
        this.id = id;
        this.customerId = customerId;
        this.orderItems = orderItems;
        this.deliveryAddress = deliveryAddress;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public DeliveryAddressInput getDeliveryAddress() {
        return deliveryAddress;
    }

    public UUID getId() {
        return id;
    }

    public List<CreateOrderItemInput> getOrderItems() {
        return orderItems;
    }
}
