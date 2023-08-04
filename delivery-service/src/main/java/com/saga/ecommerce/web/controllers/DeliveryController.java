package com.saga.ecommerce.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saga.ecommerce.core.usecase.CreateOrderDelivery;
import com.saga.ecommerce.core.usecase.inputs.CreateDeliveryOrderInput;

@RestController
@RequestMapping(value = "/deliveries")
public class DeliveryController {
    
    private CreateOrderDelivery createOrderDelivery;

    public DeliveryController(@Autowired CreateOrderDelivery createOrderDelivery) {
        this.createOrderDelivery = createOrderDelivery;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = false)
    public ResponseEntity<?> createOrderDelivery(@RequestBody CreateDeliveryOrderInput requestBody) {
        this.createOrderDelivery.execute(requestBody);
        return ResponseEntity.noContent().build();
    }
}
