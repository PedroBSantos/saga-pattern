package com.saga.ecommerce.core.domain;

import java.util.UUID;

public interface CustomerRepository {
    
    Customer findBy(UUID id);
}
