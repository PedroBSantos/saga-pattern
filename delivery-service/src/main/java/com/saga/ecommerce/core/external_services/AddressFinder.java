package com.saga.ecommerce.core.external_services;

import com.saga.ecommerce.core.domain.Address;

public interface AddressFinder {
    
    Address find(String cep, String number);
}
