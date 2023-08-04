package com.saga.ecommerce.core.domain;

import java.util.UUID;

public interface OrderRepository {

    void add(Order order);

    void remove(UUID id);

    void update(Order order);

    Order findBy(UUID id);
}
