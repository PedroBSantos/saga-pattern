package com.saga.ecommerce.core.orchestrator.step_error_observers.delivery;

import com.saga.ecommerce.core.orchestrator.inputs.OrderCreated;

public interface ErrorDeliveryRegistrationObserver {
    
    void update(OrderCreated orderCreated);
}
