package com.saga.ecommerce.core.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saga.ecommerce.core.api.exceptions.ProductNotFoundException;
import com.saga.ecommerce.core.api.inputs.DecreaseProductStockInput;
import com.saga.ecommerce.core.domain.ProductRepository;

@Service
public class DecreaseProductStock {
    
    private ProductRepository productRepository;

    public DecreaseProductStock(@Autowired ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void execute(DecreaseProductStockInput input) {
        var productId = input.getProductId();
        var product = this.productRepository.find(productId);
        if (product == null)
            throw new ProductNotFoundException("Product with id " + productId.toString() + " not found");
        product.decreaseStock(input.getQuantity());
        this.productRepository.update(product);
    }
}
