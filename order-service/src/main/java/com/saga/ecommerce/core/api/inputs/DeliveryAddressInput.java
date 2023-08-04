package com.saga.ecommerce.core.api.inputs;

public class DeliveryAddressInput {

    private String cep;
    private String number;

    protected DeliveryAddressInput() {
    }

    public DeliveryAddressInput(String cep, String number) {
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
