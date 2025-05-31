package com.shopzone.app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shopzone.app.entity.Category;
import com.shopzone.app.repo.CategoryRepo;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	public void createCategory(Category category) {
		category.setCreatedAt(LocalDateTime.now());
		categoryRepo.save(category);
		
	}

	public List<Category> getAllCategories() {
		
		return categoryRepo.findAll();
	}

}
