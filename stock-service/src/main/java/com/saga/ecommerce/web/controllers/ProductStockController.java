package com.saga.ecommerce.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saga.ecommerce.core.api.*;
import com.saga.ecommerce.core.api.inputs.*;

@RestController
@RequestMapping(value = "/product-stock")
public class ProductStockController {

    @Autowired
    private IncreaseProductStock increaseProductStock;
    @Autowired
    private DecreaseProductStock decreaseProductStock;

    @PutMapping(value = "/increase-stock", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = false)
    public ResponseEntity<?> increaseProductStock(@RequestBody IncreaseProductStockInput requestBody) {
        this.increaseProductStock.execute(requestBody);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/decrease-stock", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = false)
    public ResponseEntity<?> decreaseProductStock(@RequestBody DecreaseProductStockInput requestBody) {
        this.decreaseProductStock.execute(requestBody);
        return ResponseEntity.noContent().build();
    }
}
