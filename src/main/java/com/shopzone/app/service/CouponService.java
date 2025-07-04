package com.shopzone.app.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopzone.app.dto.CouponDto;
import com.shopzone.app.entity.Coupon;
import com.shopzone.app.repo.CouponRepo;

@Service
public class CouponService {

	@Autowired
	CouponRepo couponRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public void saveCoupon(CouponDto coupon) {
		Coupon entity= modelMapper.map(coupon, Coupon.class);
		entity.setCreateAt(LocalDateTime.now());
		entity.setCreatedBy("admin");
		System.out.println("Coupon object to be save: "+entity.toString());
		couponRepo.save(entity);
	}

	public List<Coupon> getAllCoupons() {
		
		return couponRepo.findAll();
	}

}
