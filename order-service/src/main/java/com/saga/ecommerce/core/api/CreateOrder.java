package com.saga.ecommerce.core.api;

import java.time.LocalDateTime;
import java.util.UUID;

import com.saga.ecommerce.core.api.events.OrderCreatedEvent;
import com.saga.ecommerce.core.api.inputs.CreateOrderInput;
import com.saga.ecommerce.core.domain.*;

public class CreateOrder extends OrderCreatedSubject {

    private OrderRepository orderRepository;

    public CreateOrder(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void execute(CreateOrderInput orderInput) {
        var order = new Order.OrderFactory()
                .create(UUID.randomUUID(), orderInput.getCustomerId(), LocalDateTime.now());
        orderInput.getOrderItems()
                .forEach(item -> order.addItem(item.getProductId(), item.getAmount(), item.getProductUnitPrice()));
        this.orderRepository.add(order);
        var event = new OrderCreatedEvent(order.getId(), order.getCustomerId(), orderInput.getOrderItems(), orderInput.getDeliveryAddress());
        this.updateObservers(event);
    }
}
