package com.saga.ecommerce.core.api;

import com.saga.ecommerce.core.api.events.OrderCreatedEvent;

public interface OrderCreatedObserver {
    
    void update(OrderCreatedEvent orderCreatedEvent);
}
