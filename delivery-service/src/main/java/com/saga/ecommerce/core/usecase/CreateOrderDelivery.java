package com.saga.ecommerce.core.usecase;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saga.ecommerce.core.domain.*;
import com.saga.ecommerce.core.external_services.AddressFinder;
import com.saga.ecommerce.core.usecase.exceptions.*;
import com.saga.ecommerce.core.usecase.inputs.CreateDeliveryOrderInput;

@Service
public class CreateOrderDelivery {

    private DeliveryRepository deliveryRepository;
    private CustomerRepository customerRepository;
    private AddressFinder addressFinder;

    public CreateOrderDelivery(
            @Autowired DeliveryRepository deliveryRepository,
            @Autowired CustomerRepository customerRepository,
            @Autowired AddressFinder addressFinder) {
        this.deliveryRepository = deliveryRepository;
        this.customerRepository = customerRepository;
        this.addressFinder = addressFinder;
    }

    public void execute(CreateDeliveryOrderInput input) {
        var customer = this.customerRepository.findBy(input.getCustomerId());
        if (customer == null)
            throw new CustomerNotFoundException("Customer with id " + input.getCustomerId().toString() + " not found");
        var address = this.addressFinder.find(input.getCep(), input.getNumber());
        if (address == null)
            throw new CepNotFoundException("CEP " + input.getCep() + " not found");
        var delivery = new Delivery(UUID.randomUUID(), input.getOrderId(), input.getCustomerId(), LocalDateTime.now(), address);
        this.deliveryRepository.add(delivery);
    }
}
