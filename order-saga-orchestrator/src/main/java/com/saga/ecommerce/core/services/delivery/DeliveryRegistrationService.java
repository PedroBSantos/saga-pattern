package com.saga.ecommerce.core.services.delivery;

import com.saga.ecommerce.core.services.delivery.models.DeliveryRegistration;

public interface DeliveryRegistrationService {
    
    boolean registerPendingDelivery(DeliveryRegistration registration);
}
