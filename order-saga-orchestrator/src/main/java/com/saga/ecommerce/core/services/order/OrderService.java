package com.saga.ecommerce.core.services.order;

import java.util.UUID;

public interface OrderService {
    
    boolean remove(UUID id);

    boolean processing(UUID id);

    boolean cancel(UUID id);

    boolean finish(UUID id);
}
