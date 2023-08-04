package com.saga.ecommerce.core.domain;

public class InvalidAddressCEPException extends RuntimeException {

    public InvalidAddressCEPException(String message) {
        super(message);
    }
}
