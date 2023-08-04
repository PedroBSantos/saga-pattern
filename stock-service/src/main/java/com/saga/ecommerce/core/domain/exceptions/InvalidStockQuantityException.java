package com.saga.ecommerce.core.domain.exceptions;

public class InvalidStockQuantityException extends RuntimeException {
    
    public InvalidStockQuantityException(String message) {
        super(message);
    }
}
