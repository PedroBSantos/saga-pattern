package com.saga.ecommerce.core.api;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saga.ecommerce.core.domain.OrderRepository;
import com.saga.ecommerce.core.domain.OrderStatus;

@Service
public class OrderStatusChanger {
    
    private OrderRepository orderRepository;

    public OrderStatusChanger(@Autowired OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void changeStatus(UUID orderId, OrderStatus newStatus) {
        var order = this.orderRepository.findBy(orderId);
        if (order == null)
            throw new OrderNotFoundException("Cannot found order with id " + orderId.toString());
        order.changeStatus(newStatus);
        this.orderRepository.update(order);
    }
}
