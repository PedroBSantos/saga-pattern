package com.saga.ecommerce.infra;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saga.ecommerce.core.domain.*;

public interface ProductRepositoryJpa extends ProductRepository, JpaRepository<Product, UUID> {

    @Override
    default void update(Product product) {
        this.save(product);
    }

    @Override
    default Product find(UUID id) {
        var optionalProduct = this.findById(id);
        return optionalProduct.isPresent() ? optionalProduct.get() : null;
    }
}
