package com.shopzone.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.shopzone.app.dto.ProductDto;
import com.shopzone.app.entity.Category;
import com.shopzone.app.entity.Product;
import com.shopzone.app.service.ProductService;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;

	private static final Logger log = LoggerFactory.getLogger(ProductController.class);
	
    // Create a new product
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")      //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid ProductDto product) {
        try {
        	log.info("In create new product method");
        	//products exists verify pending
        	
        	ProductDto createdProduct = productService.createProduct(product);
        	log.info("Product created with id "+createdProduct.getId());
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        } catch (Exception e) {
        	log.error("Error creating Product ",e.getMessage());
        	e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Get product by ID
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        try {
            Product product = productService.getProductById(productId);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
//
//    // Get product by SKU
//    @GetMapping("/sku/{sku}")
//    public ResponseEntity<Product> getProductBySku(@PathVariable String sku) {
//        try {
//            Product product = productService.getProductBySku(sku);
//            return new ResponseEntity<>(product, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//    }
//
    // Get all products
    @GetMapping("all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
//
//    // Get products by category
//    @GetMapping("/category/{category}")
//    public List<Product> getProductsByCategory(@PathVariable String category) {
//        return productService.getProductsByCategory(category);
//    }
//
//    // Get products by brand
//    @GetMapping("/brand/{brand}")
//    public List<Product> getProductsByBrand(@PathVariable String brand) {
//        return productService.getProductsByBrand(brand);
//    }
//
//    // Update an existing product
//    @PutMapping("/{productId}")
//   @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")  
//    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product updatedProduct) {
//        try {
//            Product updated = productService.updateProduct(productId, updatedProduct);
//            return new ResponseEntity<>(updated, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//    }
//
    // Delete product
    @DeleteMapping("/{productId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")  
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long productId) {
        try {
            productService.deleteProduct(productId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
  
}
