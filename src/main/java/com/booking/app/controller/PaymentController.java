package com.booking.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.app.dto.PaymentDto;

@RestController
@CrossOrigin("*")
@RequestMapping("/payment")
public class PaymentController {

	public ResponseEntity<?> processPayment(PaymentDto paymentDto){
		
		
		
		return null;
		
		
	}
}
