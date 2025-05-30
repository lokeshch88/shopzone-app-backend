package com.shopzone.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopzone.app.entity.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

}
