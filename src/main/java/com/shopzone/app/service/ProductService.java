package com.shopzone.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopzone.app.dto.ProductDto;
import com.shopzone.app.entity.Product;
import com.shopzone.app.repo.ProductRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    // Create a new product
    public ProductDto createProduct(ProductDto productDto) {
        if (productDto.getDiscountPrice() != null && productDto.getMrp().compareTo(productDto.getDiscountPrice()) < 0) {
            throw new IllegalArgumentException("Discount price cannot be greater than MRP.");
        }
        
        Product product= modelMapper.map(productDto, Product.class);
        
        product.setCreatedAt(LocalDateTime.now());
        product.setIsActive(true);
        // Save entity
        Product savedProduct = productRepository.save(product);

        // Map back to DTO (optional)
        return modelMapper.map(savedProduct, ProductDto.class);
    }

    // Get product by ID
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // Get product by SKU
    public Product getProductBySku(String sku) {
        return productRepository.findBySku(sku).orElseThrow(() -> new RuntimeException("Product with SKU " + sku + " not found"));
    }

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get products by category
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    // Get products by brand
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    // Update an existing product
    public Product updateProduct(Long productId, Product updatedProduct) {
        Product existingProduct = getProductById(productId);
        
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setMrp(updatedProduct.getMrp());
        existingProduct.setDiscountPrice(updatedProduct.getDiscountPrice());
        existingProduct.setBrand(updatedProduct.getBrand());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setQuantityInStock(updatedProduct.getQuantityInStock());
        existingProduct.setWeight(updatedProduct.getWeight());
        existingProduct.setDimensions(updatedProduct.getDimensions());
        existingProduct.setImageUrl(updatedProduct.getImageUrl());
        existingProduct.setManufacturer(updatedProduct.getManufacturer());
        existingProduct.setRating(updatedProduct.getRating());
        existingProduct.setUpdatedAt(updatedProduct.getUpdatedAt());
        
        return productRepository.save(existingProduct);
    }

    // Delete product
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}

