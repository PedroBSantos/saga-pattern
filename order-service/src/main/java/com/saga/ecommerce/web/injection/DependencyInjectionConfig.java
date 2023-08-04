package com.saga.ecommerce.web.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

import com.saga.ecommerce.core.api.*;
import com.saga.ecommerce.core.domain.OrderRepository;

@Configuration
public class DependencyInjectionConfig {

    public DependencyInjectionConfig() {
    }

    @Bean
    @RequestScope
    CreateOrder createOrderConfiguration(
            @Autowired OrderRepository orderRepository,
            @Autowired OrderCreatedObserver orderCreatedObserver) {
        var createOrder = new CreateOrder(orderRepository);
        createOrder.addObserver(orderCreatedObserver);
        return createOrder;
    }
}
