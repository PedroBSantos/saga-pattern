package com.saga.ecommerce.core.compensators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saga.ecommerce.core.orchestrator.step_error_observers.stock.FailedOrderProcessed;
import com.saga.ecommerce.core.services.order.OrderService;
import com.saga.ecommerce.core.services.stock.StockProductService;
import com.saga.ecommerce.core.services.stock.models.IncreaseProductStock;

@Service
public class ErrorStockOperationCompensator {

    private StockProductService stockProductService;
    private OrderService orderService;
    
    public ErrorStockOperationCompensator(
        @Autowired StockProductService stockProductService,
        @Autowired OrderService orderService) {
        this.stockProductService = stockProductService;
        this.orderService = orderService;
    }

    public void compensate(FailedOrderProcessed orderFailed) {
        orderFailed.getOrderItems().forEach(item -> {
            var stockIncrease = new IncreaseProductStock(item.getProductId(), item.getAmount());
            this.stockProductService.increaseStockAmount(stockIncrease);
        });
        this.orderService.cancel(orderFailed.getOrderId());
    }
}
