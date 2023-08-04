package com.saga.ecommerce.core.orchestrator.step_error_observers.stock;

public interface ErrorStockOperationObserver {
    
    void update(FailedOrderProcessed failedOrderProcessed);
}
