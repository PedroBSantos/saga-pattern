package com.saga.ecommerce.core.api;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saga.ecommerce.core.api.exceptions.ProductNotFoundException;
import com.saga.ecommerce.core.domain.ProductRepository;

@Service
public class HasProductInStock {
    
    private ProductRepository productRepository;

    public HasProductInStock(@Autowired ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public boolean execute(UUID productId) {
        var product = this.productRepository.find(productId);
        if (product == null)
            throw new ProductNotFoundException("Product with id " + productId.toString() + " not found");
        return product.inStock();
    }
}
