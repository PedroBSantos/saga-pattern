package com.saga.ecommerce.infra;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saga.ecommerce.core.domain.*;

public interface CustomerRepositoryJpa extends CustomerRepository, JpaRepository<Customer, UUID> {
    
    @Override
    default Customer findBy(UUID id) {
        var optionalCustomer = this.findById(id);
        return optionalCustomer.isPresent() ? optionalCustomer.get() : null;
    }
}
