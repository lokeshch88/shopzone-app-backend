package com.shopzone.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopzone.app.dto.PaymentDto;

@RestController
@CrossOrigin("*")
@RequestMapping("/payments")
public class PaymentController {

	@PostMapping("/process")
	public ResponseEntity<?> processPayment(PaymentDto paymentDto){
		try {
			paymentDto.setPaymentStatus("SUCCESS");
//			paymentDto.setPaymentStatus("FAILED");
			
			return new ResponseEntity<>(paymentDto, HttpStatus.OK);
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return null;
		
		
	}
}
