package com.shopzone.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopzone.app.dto.OrderRequest;
import com.shopzone.app.dto.OrderResponse;
import com.shopzone.app.entity.Order;
import com.shopzone.app.entity.OrderStatus;
import com.shopzone.app.entity.User;
import com.shopzone.app.repo.UserRepo;
import com.shopzone.app.service.OrderService;



@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrderController {

	@Autowired
    private OrderService OrderService;
	@Autowired
	private UserRepo userRepo;

    @PostMapping("/user/{userId}")
    public ResponseEntity<OrderResponse> createOrder(
            @PathVariable Long userId,
            @RequestBody OrderRequest request) {
        return ResponseEntity.ok(OrderService.createOrder(userId, request));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderResponse> updateStatus(
            @PathVariable Long id,
            @RequestParam OrderStatus status) {
        return ResponseEntity.ok(OrderService.updateOrderStatus(id, status));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderResponse>> getUserOrders(@PathVariable Long userId) {
        return ResponseEntity.ok(OrderService.getOrdersForUser(userId));
    }
    
    //to avoid anyone can fetch anyones order by passing id
    @GetMapping("/my")
    public ResponseEntity<List<OrderResponse>> getMyOrders(Authentication authentication){
    	String username = authentication.getName(); // or extract email
        User user = userRepo.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<OrderResponse> orders = OrderService.getOrdersForUser(user.getId());
        return ResponseEntity.ok(orders);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(OrderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(OrderService.getOrderById(id));
    }

    @DeleteMapping("/{id}/user/{userId}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long id, @PathVariable Long userId) {
        OrderService.cancelOrder(id, userId);
        return ResponseEntity.noContent().build();
    }
}

