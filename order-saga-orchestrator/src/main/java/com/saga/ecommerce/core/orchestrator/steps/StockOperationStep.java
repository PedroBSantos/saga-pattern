package com.saga.ecommerce.core.orchestrator.steps;

import java.util.ArrayList;

import com.saga.ecommerce.core.orchestrator.inputs.OrderCreated;
import com.saga.ecommerce.core.orchestrator.inputs.OrderItem;
import com.saga.ecommerce.core.orchestrator.step_error_observers.stock.ErrorStockOperationSubject;
import com.saga.ecommerce.core.orchestrator.step_error_observers.stock.FailedOrderProcessed;
import com.saga.ecommerce.core.services.stock.StockProductService;
import com.saga.ecommerce.core.services.stock.models.DecreaseProductStock;

public class StockOperationStep extends ErrorStockOperationSubject {

    private StockProductService stockProductService;

    public StockOperationStep(StockProductService stockProductService) {
        this.stockProductService = stockProductService;
    }

    public boolean execute(OrderCreated orderCreated) {
        var successfullDecreased = new ArrayList<OrderItem>();
        for (var item : orderCreated.getOrderItems()) {
            var operation = new DecreaseProductStock(item.getProductId(), item.getAmount());
            var successfull = this.stockProductService.decreaseStockAmount(operation);
            if (successfull) {
                successfullDecreased.add(item);
                continue;
            }
            var failuredOrder = new FailedOrderProcessed(orderCreated.getId(), successfullDecreased);
            this.updateObservers(failuredOrder);
        }
        return true;
    }
}
