package com.saga.ecommerce.core.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saga.ecommerce.core.api.exceptions.ProductNotFoundException;
import com.saga.ecommerce.core.api.inputs.IncreaseProductStockInput;
import com.saga.ecommerce.core.domain.ProductRepository;

@Service
public class IncreaseProductStock {

    private ProductRepository productRepository;

    public IncreaseProductStock(@Autowired ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void execute(IncreaseProductStockInput input) {
        var productId = input.getProductId();
        var product = this.productRepository.find(productId);
        if (product == null)
            throw new ProductNotFoundException("Product with id " + productId.toString() + " not found");
        product.increaseStock(input.getQuantity());
        this.productRepository.update(product);
    }
}
