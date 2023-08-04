package com.saga.ecommerce.core.domain;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, updatable = false)
    private UUID productId;
    @Column(nullable = false, updatable = false)
    private int amount;
    @Column(nullable = false, updatable = false)
    private double productUnitPrice;
    @ManyToOne
    @JoinColumn(name = "id_order")
    private Order order;

    protected OrderItem() {
    }

    public OrderItem(Order order, UUID productId, int amount, double productUnitPrice) {
        this.productId = productId;
        this.amount = amount;
        this.productUnitPrice = productUnitPrice;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public UUID getProductId() {
        return productId;
    }

    public int getAmount() {
        return amount;
    }

    public Order getOrder() {
        return order;
    }

    public void changeAmount(int newAmount) {
        if (newAmount <= 0)
            throw new RuntimeException("Amount must be greater than 0");
        this.amount = newAmount;
    }

    public double getProductUnitPrice() {
        return productUnitPrice;
    }

    public double subTotal() {
        return this.amount * this.productUnitPrice;
    }
}
