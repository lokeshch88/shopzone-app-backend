package com.shopzone.app.controller;

import java.net.ResponseCache;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopzone.app.dto.CouponDto;
import com.shopzone.app.dto.ResponseDto;
import com.shopzone.app.entity.Coupon;
import com.shopzone.app.service.CouponService;

@RestController
@RequestMapping("/coupons")
@CrossOrigin("*")
public class CouponController {
	@Autowired
	CouponService couponService;

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	@PostMapping("/create")
	public ResponseEntity<ResponseDto<Coupon>> createCoupon(@RequestBody CouponDto coupon) {
		log.info("coupon reqs data received: "+coupon);
		couponService.saveCoupon(coupon);
		ResponseDto<Coupon> resp = new ResponseDto<Coupon>();
		resp.setMessage("Coupon saved");
		resp.setStatus(0);

		return ResponseEntity.ok(resp);

	}
	
	@GetMapping("/all")
	public ResponseEntity<ResponseDto<Coupon>> fetchAllCoupons(){
		log.info("Inside fetch allcoupon req: ");
		List<Coupon> coupons= couponService.getAllCoupons();
		ResponseDto<Coupon> resp = new ResponseDto<Coupon>();
		resp.setMessage("Coupons fetched successfully");
		resp.setResult(coupons);
		resp.setStatus(0);
		return ResponseEntity.ok(resp);
	}
}
