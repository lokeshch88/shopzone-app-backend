package com.shopzone.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopzone.app.dto.PaymentDto;
import com.shopzone.utils.RandomCodeUtil;

@RestController
@CrossOrigin("*")
@RequestMapping("/payments")
public class PaymentController {

	@PostMapping("/process")
	public ResponseEntity<?> processPayment(PaymentDto paymentDto){
		try {
			System.out.println("In paytemenet controller method");
			paymentDto.setPaymentStatus("SUCCESS");
//			paymentDto.setPaymentStatus("FAILED");
			String txnId= RandomCodeUtil.generateTransactionId();
			paymentDto.setTxnId(txnId);
			return new ResponseEntity<>(paymentDto, HttpStatus.OK);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		
		
		
		
	}
}
