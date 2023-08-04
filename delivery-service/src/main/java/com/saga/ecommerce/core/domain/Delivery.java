package com.saga.ecommerce.core.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "deliveries")
public class Delivery {

    @Id
    @Column(nullable = false, updatable = false, unique = true)
    private UUID id;
    @Column(nullable = false, updatable = false)
    private UUID orderId;
    @Column(nullable = false, updatable = false)
    private UUID customerId;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Embedded
    private Address deliveryAddress;

    protected Delivery() {
    }

    public Delivery(UUID id, UUID orderId, UUID customerId, LocalDateTime createdAt, Address deliveryAddress) {
        this.id = id;
        this.orderId = orderId;
        this.customerId = customerId;
        this.createdAt = createdAt;
        this.deliveryAddress = deliveryAddress;
    }

    public UUID getId() {
        return id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Delivery other = (Delivery) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
