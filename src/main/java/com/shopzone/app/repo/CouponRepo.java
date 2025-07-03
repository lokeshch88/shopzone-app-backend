package com.shopzone.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopzone.app.entity.Coupon;

@Repository
public interface CouponRepo extends JpaRepository<Coupon, Long> {

}
