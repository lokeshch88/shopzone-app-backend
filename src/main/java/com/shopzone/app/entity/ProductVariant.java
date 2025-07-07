package com.shopzone.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product_variant")
public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private String size;     // S, M, L, XL, etc.
    private String color;    // Red, Blue, etc.
    private Double price;
    private Integer stock;

    private String imageUrl; // image specific to variant

    // Unique combination
//    @Column(unique = true)
    private String sku; // Stock Keeping Unit like "TSHIRT-M-RED"

    // Getters/setters...
    
    
}
