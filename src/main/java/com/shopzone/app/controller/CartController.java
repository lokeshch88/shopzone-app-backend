package com.shopzone.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopzone.app.dto.CartDto;
import com.shopzone.app.service.CartService;

@RestController
@RequestMapping("/cart")
@CrossOrigin("*")
public class CartController {
	
	@Autowired
	private CartService cartService;

	@PostMapping("/save")
public ResponseEntity<CartDto> saveCart(@RequestBody CartDto cartDto){
	cartService.saveCart(cartDto);
		return null;
	
}
}
