package com.shopzone.app.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.shopzone.app.controller.UserController;
import com.shopzone.app.dto.ProductDto;
import com.shopzone.app.dto.UserDto;
import com.shopzone.app.entity.Category;
import com.shopzone.app.entity.Product;
import com.shopzone.app.entity.ProductVariant;
import com.shopzone.app.repo.CategoryRepo;
import com.shopzone.app.repo.ProductRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private CategoryRepo categoryRepository;

	private static final Logger log = LoggerFactory.getLogger(ProductService.class);
//	public ProductDto createProduct(ProductDto productDto) {
//		
//		try {
//		if (productDto.getDiscountPrice() != null && productDto.getMrp().compareTo(productDto.getDiscountPrice()) < 0) {
//			throw new IllegalArgumentException("Discount price cannot be greater than MRP.");
//		}
//
//		Product product = modelMapper.map(productDto, Product.class);
//	System.out.println("product mapped");
//		product.setCategoryId(categoryRepo.findById(productDto.getCategoryId())
//				.orElseThrow(() -> new RuntimeException("Category not found")));
//		System.out.println("category id fetched ");
//		product.setCreatedAt(LocalDateTime.now());
//		product.setIsActive(true);
//		Product savedProduct = productRepository.save(product);
//
//		// Map 
//		return modelMapper.map(savedProduct, ProductDto.class);
//	}catch (Exception e) {
//		e.printStackTrace();
//		return null;
//	}
//	}

	public ProductDto createProduct(ProductDto productDto) {
		log.info("In create new product service for " + productDto);
		try {
			if (productDto.getDiscountPrice() != null
					&& productDto.getMrp().compareTo(productDto.getDiscountPrice()) < 0) {
				throw new IllegalArgumentException("Discount price cannot be greater than MRP.");
			}

			// Map DTO to Entity
			Product product = modelMapper.map(productDto, Product.class);
			product.setCategoryId(categoryRepo.findById(productDto.getCategoryId())
					.orElseThrow(() -> new RuntimeException("Category not found")));

			product.setCreatedAt(LocalDateTime.now());
			product.setIsActive(true);

			// Map & set product in each variant
			if (product.getVariants() != null) {
				for (ProductVariant variant : product.getVariants()) {
					variant.setProduct(product); // IMPORTANT for bidirectional mapping
				}
			}

			// Save product (with cascade it will save variants too)
			Product savedProduct = productRepository.save(product);

			return modelMapper.map(savedProduct, ProductDto.class);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Product getProductById(Long productId) {
		return productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
	}

	public ProductDto getProductDtoById(Long productId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found"));
		ProductDto dto = modelMapper.map(product, ProductDto.class);
		return dto;
	}

	public Product getProductBySku(String sku) {
		return productRepository.findBySku(sku)
				.orElseThrow(() -> new RuntimeException("Product with SKU " + sku + " not found"));
	}

	public List<ProductDto> getAllProducts() {
		List<Product> products = productRepository.findAll();
		return products.stream().map(product -> modelMapper.map(product, ProductDto.class))
				.collect(Collectors.toList());
	}


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

		// handle variants correctly
		if (updatedProduct.getVariants() != null) {
			existingProduct.getVariants().clear(); // orphanRemoval triggers delete

			for (ProductVariant variant : updatedProduct.getVariants()) {
				variant.setProduct(existingProduct); // assign parent
				existingProduct.getVariants().add(variant); // add to existing list
			}
		}

		return productRepository.save(existingProduct);
	}

	public void deleteProduct(Long productId) {
		productRepository.deleteById(productId);
	}

	public List<ProductDto> getProductsByCategory(Long categoryId) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new RuntimeException("Category not found"));

		List<Product> products = productRepository.findByCategoryId(category);

		return products.stream().map(product -> modelMapper.map(product, ProductDto.class))
				.collect(Collectors.toList());
	}
}
