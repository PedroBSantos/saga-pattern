package com.saga.ecommerce.web.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saga.ecommerce.core.api.CreateOrder;
import com.saga.ecommerce.core.api.OrderStatusChanger;
import com.saga.ecommerce.core.api.inputs.CreateOrderInput;
import com.saga.ecommerce.core.domain.OrderRepository;
import com.saga.ecommerce.core.domain.OrderStatus;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private OrderRepository orderRepository;
    private CreateOrder createOrder;
    private OrderStatusChanger orderStatusChanger;

    public OrderController(
            @Autowired CreateOrder createOrder, 
            @Autowired OrderRepository orderRepository,
            @Autowired OrderStatusChanger orderStatusChanger) {
        this.createOrder = createOrder;
        this.orderRepository = orderRepository;
        this.orderStatusChanger = orderStatusChanger;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = false)
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderInput createOrderInput) {
        this.createOrder.execute(createOrderInput);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping(value = "/{id}")
    @Transactional(readOnly = false)
    public ResponseEntity<?> deleteOrder(@PathVariable UUID id) {
        this.orderRepository.remove(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/processing/{id}")
    @Transactional(readOnly = false)
    public ResponseEntity<?> markOrderAsProcessing(@PathVariable UUID id) {
        this.orderStatusChanger.changeStatus(id, OrderStatus.PROCESSING);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/cancel/{id}")
    @Transactional(readOnly = false)
    public ResponseEntity<?> cancelOrder(@PathVariable UUID id) {
        this.orderStatusChanger.changeStatus(id, OrderStatus.CANCELED);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/finish/{id}")
    @Transactional(readOnly = false)
    public ResponseEntity<?> finishOrder(@PathVariable UUID id) {
        this.orderStatusChanger.changeStatus(id, OrderStatus.FINISHED);
        return ResponseEntity.ok().build();
    }
}
