package com.saga.ecommerce.core.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(updatable = false, nullable = false)
    private UUID id;
    @Column(updatable = false, nullable = false)
    private UUID customerId;
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "order", cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<OrderItem> orderItems;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    protected Order() {
        this.orderItems = new ArrayList<>();
    }

    private Order(UUID id, UUID customerId, LocalDateTime createdAt, OrderStatus status) {
        this();
        this.id = id;
        this.customerId = customerId;
        this.createdAt = createdAt;
        this.orderItems = new ArrayList<>();
        this.status = status;
    }

    public UUID getId() {
        return this.id;
    }

    public UUID getCustomerId() {
        return this.customerId;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public List<OrderItem> getOrderItems() {
        return this.orderItems.stream().collect(Collectors.toList());
    }

    public void changeStatus(OrderStatus status) {
        this.status = status;
    }

    public void addItem(UUID productId, int amount, double productUnitPrice) {
        var item = new OrderItem(this, productId, amount, productUnitPrice);
        this.orderItems.add(item);
    }

    public double total() {
        var total = 0.0;
        for (var orderItem : this.orderItems)
            total += orderItem.subTotal();
        return total;
    }

    public boolean validate() {
        if (orderItems.isEmpty())
            throw new RuntimeException("Empty list of order items");
        if (id == null)
            throw new RuntimeException("OrderId must not be null");
        if (customerId == null)
            throw new RuntimeException("CustomerId must not be null");
        if (createdAt == null)
            throw new RuntimeException("CreatedAt must not be null");
        return true;
    }

    public static class OrderFactory {

        public Order create(UUID id, UUID customerId, LocalDateTime createdAt) {
            var order = new Order(id, customerId, createdAt, OrderStatus.CREATED);
            return order;
        }
    }
}
