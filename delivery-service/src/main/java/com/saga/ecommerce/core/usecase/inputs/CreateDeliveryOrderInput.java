package com.saga.ecommerce.core.usecase.inputs;

import java.util.UUID;

public class CreateDeliveryOrderInput {
    
    private UUID orderId;
    private UUID customerId;
    private String cep;
    private String number;

    protected CreateDeliveryOrderInput() {
    }

    public CreateDeliveryOrderInput(UUID orderId, UUID customerId, String cep, String number) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.cep = cep;
        this.number = number;
    }

    public String getCep() {
        return cep;
    }

    public UUID getCustomerId() {
        return customerId;
    }
    
    public String getNumber() {
        return number;
    }

    public UUID getOrderId() {
        return orderId;
    }
}
