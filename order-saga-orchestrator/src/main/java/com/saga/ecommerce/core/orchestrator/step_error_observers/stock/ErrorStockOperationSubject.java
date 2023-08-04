package com.saga.ecommerce.core.orchestrator.step_error_observers.stock;

import java.util.ArrayList;
import java.util.List;

public abstract class ErrorStockOperationSubject {

    private List<ErrorStockOperationObserver> observers;

    protected ErrorStockOperationSubject() {
        this.observers = new ArrayList<>();
    }

    public void addObserver(ErrorStockOperationObserver observer) {
        if (observer == null)
            return;
        this.observers.add(observer);
    }

    public void updateObservers(FailedOrderProcessed failedOrderProcessed) {
        this.observers.forEach(observer -> observer.update(failedOrderProcessed));
    }
}
