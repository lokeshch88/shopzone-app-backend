package com.shopzone.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopzone.app.entity.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long> {

}
