package com.shopzone.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "variant_id")
    private ProductVariant productVariant;

    private int quantity;

    @Column(name = "price_at_addition") // capture price at time of adding to cart
    private Double price;

    @Column(name = "total_price")
    private Double totalPrice;

    @PrePersist
    @PreUpdate
    public void calculateTotal() {
        this.totalPrice = this.price * this.quantity;
    }

    // Getters/setters...
}
