package com.saga.ecommerce.core.compensators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saga.ecommerce.core.orchestrator.inputs.OrderCreated;
import com.saga.ecommerce.core.orchestrator.step_error_observers.stock.FailedOrderProcessed;

@Service
public class ErrorDeliveryCompensator {
    
    private ErrorStockOperationCompensator compensator;

    public ErrorDeliveryCompensator(@Autowired ErrorStockOperationCompensator compensator) {
        this.compensator = compensator;
    }

    public void compensate(OrderCreated orderCreated) {
        var orderFailed = new FailedOrderProcessed(orderCreated.getId(), orderCreated.getOrderItems());
        this.compensator.compensate(orderFailed);
    }
}
