package com.saga.ecommerce.core.orchestrator.step_error_observers.delivery;

import java.util.ArrayList;
import java.util.List;

import com.saga.ecommerce.core.orchestrator.inputs.OrderCreated;

public abstract class ErrorDeliveryRegistrationSubject {
    
    private List<ErrorDeliveryRegistrationObserver> observers;

    protected ErrorDeliveryRegistrationSubject() {
        this.observers = new ArrayList<>();
    }

    public void addObserver(ErrorDeliveryRegistrationObserver observer) {
        if (observer == null)
            return;
        this.observers.add(observer);
    }

    public void updateObservers(OrderCreated orderCreated) {
        this.observers.forEach(observer -> observer.update(orderCreated));
    }
}
