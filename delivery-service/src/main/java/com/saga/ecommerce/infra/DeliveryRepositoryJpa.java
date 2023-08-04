package com.saga.ecommerce.infra;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saga.ecommerce.core.domain.*;

public interface DeliveryRepositoryJpa extends DeliveryRepository, JpaRepository<Delivery, UUID> {
    
    @Override
    default void add(Delivery delivery) {
        this.save(delivery);
    }
}
