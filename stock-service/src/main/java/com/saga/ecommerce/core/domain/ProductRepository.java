package com.saga.ecommerce.core.domain;

import java.util.UUID;

public interface ProductRepository {

    void update(Product product);

    Product find(UUID id);
}
