package com.shopzone.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopzone.app.entity.Category;
import com.shopzone.app.service.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {
	  
    
	@Autowired
    private CategoryService categoryService;

	private static final Logger log = LoggerFactory.getLogger(CategoryController.class);
	
	@PostMapping("/create")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Category> createCategory(@RequestBody Category category){
    	log.info("In create category method for name "+category.getName());
    	try {
    		categoryService.createCategory(category);
    		return ResponseEntity.ok(category);
    	}catch (Exception e) {
    		return null;
		}
		
    }
	
	
    
}
