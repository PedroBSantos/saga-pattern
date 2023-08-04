package com.saga.ecommerce.core.services.delivery.models;

import java.util.UUID;

public class DeliveryRegistration {
    
    private UUID orderId;
    private UUID customerId;
    private String cep;
    private String number;

    public DeliveryRegistration(UUID orderId, UUID customerId, String cep, String number) {
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
