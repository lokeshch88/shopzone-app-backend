package com.shopzone.app.controller;

import java.net.ResponseCache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopzone.app.dto.CouponDto;
import com.shopzone.app.dto.ResponseDto;
import com.shopzone.app.entity.Coupon;
import com.shopzone.app.service.CouponService;

@RestController
@RequestMapping("/coupon")
@CrossOrigin("*")
public class CouponController {
	@Autowired
	CouponService couponService;

	@PostMapping("/create")
	public ResponseEntity<ResponseDto<Coupon>> createCoupon(@RequestBody CouponDto coupon) {
		System.out.println("coupon reqs data received: "+coupon);
		couponService.saveCoupon(coupon);
		ResponseDto<Coupon> resp = new ResponseDto<Coupon>();
		resp.setMessage("Coupon saved");
		resp.setStatus(0);

		return ResponseEntity.ok(resp);

	}
}
