package com.saga.ecommerce.core.orchestrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saga.ecommerce.core.orchestrator.inputs.OrderCreated;
import com.saga.ecommerce.core.orchestrator.steps.*;
import com.saga.ecommerce.core.services.order.OrderService;

@Service
public class OrderCreatedSagaOrchestrator {

    private StockOperationStep stockOperationStep;
    private DeliveryOperationStep deliveryOperationStep;
    private OrderService orderService;

    public OrderCreatedSagaOrchestrator(
            @Autowired StockOperationStep stockOperationStep,
            @Autowired DeliveryOperationStep deliveryOperationStep,
            @Autowired OrderService orderService) {
        this.stockOperationStep = stockOperationStep;
        this.deliveryOperationStep = deliveryOperationStep;
        this.orderService = orderService;
    }

    public void orchestrate(OrderCreated orderCreated) {
        this.orderService.processing(orderCreated.getId());
        var successfull = this.stockOperationStep.execute(orderCreated);
        if (!successfull)
            return;
        successfull = this.deliveryOperationStep.execute(orderCreated);
        if (!successfull)
            return;
        this.orderService.finish(orderCreated.getId());
    }
}
