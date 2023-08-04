package com.saga.ecommerce.core.usecase.exceptions;

public class CepNotFoundException extends RuntimeException {
    
    public CepNotFoundException(String message) {
        super(message);
    }
}
