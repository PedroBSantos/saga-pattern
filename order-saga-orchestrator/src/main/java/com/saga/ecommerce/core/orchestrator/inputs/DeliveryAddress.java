package com.saga.ecommerce.core.orchestrator.inputs;

public class DeliveryAddress {

    private String cep;
    private String number;

    protected DeliveryAddress() {
    }

    public DeliveryAddress(String cep, String number) {
        this.cep = cep;
        this.number = number;
    }

    public String getCep() {
        return cep;
    }

    public String getNumber() {
        return number;
    }
}
