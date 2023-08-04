package com.saga.ecommerce.infra;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saga.ecommerce.core.domain.*;

public interface OrderRepositoryJpa extends OrderRepository, JpaRepository<Order, UUID> {

    @Override
    default void add(Order order) {
        this.save(order);
    }

    @Override
    default void remove(UUID id) {
        this.deleteById(id);
    }

    @Override
    default void update(Order order) {
        this.save(order);
    }

    @Override
    default Order findBy(UUID id) {
        var optionalOrder = this.findById(id);
        return optionalOrder.isPresent() ? optionalOrder.get() : null;
    }
}
