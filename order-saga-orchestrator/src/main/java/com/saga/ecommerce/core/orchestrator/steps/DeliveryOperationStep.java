package com.saga.ecommerce.core.orchestrator.steps;

import org.springframework.beans.factory.annotation.Autowired;

import com.saga.ecommerce.core.orchestrator.inputs.OrderCreated;
import com.saga.ecommerce.core.orchestrator.step_error_observers.delivery.ErrorDeliveryRegistrationSubject;
import com.saga.ecommerce.core.services.delivery.DeliveryRegistrationService;
import com.saga.ecommerce.core.services.delivery.models.DeliveryRegistration;

public class DeliveryOperationStep extends ErrorDeliveryRegistrationSubject {

    private DeliveryRegistrationService deliveryRegistrationService;

    public DeliveryOperationStep(@Autowired DeliveryRegistrationService deliveryRegistrationService) {
        this.deliveryRegistrationService = deliveryRegistrationService;
    }

    public boolean execute(OrderCreated orderCreated) {
        var deliveryRegistration = new DeliveryRegistration(
                orderCreated.getId(),
                orderCreated.getCustomerId(),
                orderCreated.getCepToDelivery(),
                orderCreated.getNumberToDelivery());
        var successfull = this.deliveryRegistrationService.registerPendingDelivery(deliveryRegistration);
        if (!successfull) {
            this.updateObservers(orderCreated);
            return successfull;
        }
        return successfull;
    }
}
