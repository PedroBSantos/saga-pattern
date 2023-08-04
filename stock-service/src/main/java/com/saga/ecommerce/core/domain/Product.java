package com.saga.ecommerce.core.domain;

import java.util.UUID;

import com.saga.ecommerce.core.domain.exceptions.InvalidStockQuantityException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(nullable = false, updatable = false, unique = true)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private int stockAmount;

    protected Product() {
    }

    public Product(UUID id, String name, double price, int stockAmount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockAmount = stockAmount;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStockAmount() {
        return stockAmount;
    }

    public boolean inStock() {
        return this.stockAmount > 0;
    }

    public void increaseStock(int quantity) {
        if (quantity <= 0)
            throw new InvalidStockQuantityException("Quantity must be greater than 0");
        this.stockAmount += quantity;
    }

    public void decreaseStock(int quantity) {
        if (quantity > this.stockAmount)
            throw new InvalidStockQuantityException("Quantity overflows current amount on stock: " + this.stockAmount);
        if (quantity <= 0)
            throw new InvalidStockQuantityException("Quantity must be greater than 0");
        this.stockAmount -= quantity;
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
        Product other = (Product) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
