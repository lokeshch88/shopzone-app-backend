package com.shopzone.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopzone.app.dto.ProductDto;
import com.shopzone.app.entity.Product;
import com.shopzone.app.repo.CategoryRepo;
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

	@Autowired
	private CategoryRepo categoryRepo;

	
	public ProductDto createProduct(ProductDto productDto) {
		
		try {
		if (productDto.getDiscountPrice() != null && productDto.getMrp().compareTo(productDto.getDiscountPrice()) < 0) {
			throw new IllegalArgumentException("Discount price cannot be greater than MRP.");
		}

		Product product = modelMapper.map(productDto, Product.class);
	System.out.println("product mapped");
		product.setCategoryId(categoryRepo.findById(productDto.getCategoryId())
				.orElseThrow(() -> new RuntimeException("Category not found")));
		System.out.println("category id fetched ");
		product.setCreatedAt(LocalDateTime.now());
		product.setIsActive(true);
		Product savedProduct = productRepository.save(product);

		// Map 
		return modelMapper.map(savedProduct, ProductDto.class);
	}catch (Exception e) {
		e.printStackTrace();
		return null;
	}
	}

	
	

	public Product getProductById(Long productId) {
		return productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
	}


	public Product getProductBySku(String sku) {
		return productRepository.findBySku(sku)
				.orElseThrow(() -> new RuntimeException("Product with SKU " + sku + " not found"));
	}


	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}


//	public List<Product> getProductsByCategory(String category) {
//		return productRepository.findByCategory(category);
//	}


	public List<Product> getProductsByBrand(String brand) {
		return productRepository.findByBrand(brand);
	}


	public Product updateProduct(Long productId, Product updatedProduct) {
		Product existingProduct = getProductById(productId);

		existingProduct.setName(updatedProduct.getName());
		existingProduct.setDescription(updatedProduct.getDescription());
		existingProduct.setPrice(updatedProduct.getPrice());
		existingProduct.setMrp(updatedProduct.getMrp());
		existingProduct.setDiscountPrice(updatedProduct.getDiscountPrice());
		existingProduct.setBrand(updatedProduct.getBrand());
		existingProduct.setQuantityInStock(updatedProduct.getQuantityInStock());
		existingProduct.setWeight(updatedProduct.getWeight());
		existingProduct.setDimensions(updatedProduct.getDimensions());
		existingProduct.setImageUrl(updatedProduct.getImageUrl());
		existingProduct.setManufacturer(updatedProduct.getManufacturer());
		existingProduct.setRating(updatedProduct.getRating());
		existingProduct.setUpdatedAt(updatedProduct.getUpdatedAt());

		return productRepository.save(existingProduct);
	}


	public void deleteProduct(Long productId) {
		productRepository.deleteById(productId);
	}
}
