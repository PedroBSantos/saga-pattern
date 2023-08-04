package com.saga.ecommerce.core.api;

import java.util.ArrayList;
import java.util.List;

import com.saga.ecommerce.core.api.events.OrderCreatedEvent;

public abstract class OrderCreatedSubject {
    
    private List<OrderCreatedObserver> observers;

    protected OrderCreatedSubject() {
        this.observers = new ArrayList<>();
    }

    public void addObserver(OrderCreatedObserver observer) {
        if (observer == null) return;
        this.observers.add(observer);
    }

    protected void updateObservers(OrderCreatedEvent orderCreatedEvent) {
        this.observers.forEach(observer -> observer.update(orderCreatedEvent));
    }
}
