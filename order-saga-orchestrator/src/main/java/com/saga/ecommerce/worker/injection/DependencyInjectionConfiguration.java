package com.saga.ecommerce.worker.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.saga.ecommerce.core.orchestrator.steps.DeliveryOperationStep;
import com.saga.ecommerce.core.orchestrator.steps.StockOperationStep;
import com.saga.ecommerce.core.services.delivery.DeliveryRegistrationService;
import com.saga.ecommerce.core.services.stock.StockProductService;
import com.saga.ecommerce.worker.rabbitmq.publishers.ErrorDeliveryRegistrationPublisher;
import com.saga.ecommerce.worker.rabbitmq.publishers.ErrorStockOperationPublisher;

@Configuration
public class DependencyInjectionConfiguration {

    public DependencyInjectionConfiguration() {
    }

    @Bean
    StockOperationStep stockOperationStep(
            @Autowired StockProductService stockProductService,
            @Autowired ErrorStockOperationPublisher publisher) {
        var stockOperationStep = new StockOperationStep(stockProductService);
        stockOperationStep.addObserver(publisher);
        return stockOperationStep;
    }

    @Bean
    DeliveryOperationStep deliveryOperationStep(
            @Autowired DeliveryRegistrationService deliveryRegistrationService,
            @Autowired ErrorDeliveryRegistrationPublisher publisher) {
        var deliveryOperationStep = new DeliveryOperationStep(deliveryRegistrationService);
        deliveryOperationStep.addObserver(publisher);
        return deliveryOperationStep;
    }
}
