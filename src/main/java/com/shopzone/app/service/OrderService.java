package com.shopzone.app.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopzone.app.dto.OrderRequest;
import com.shopzone.app.dto.OrderResponse;
import com.shopzone.app.entity.Order;
import com.shopzone.app.entity.OrderStatus;
import com.shopzone.app.repo.OrderRepository;
import com.shopzone.app.repo.UserRepo;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepo userRepository;


    public OrderResponse createOrder(Long userId, OrderRequest request) {
        Order order = new Order();
        order.setUser(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));
        order.setProductIds(request.getProductIds()); 
        order.setStatus(OrderStatus.PENDING); // default status
        order.setTotalAmount(request.getTotalAmount());
        
        Order savedOrder = orderRepository.save(order);
        return toResponse(savedOrder);
    }


    public OrderResponse updateOrderStatus(Long orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        return toResponse(orderRepository.save(order));
    }

    public List<OrderResponse> getOrdersForUser(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);  //
        
        return orders.stream()
                     .map(this::toResponse)                         
                     .collect(Collectors.toList());
    }



    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    //  by ID
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        return toResponse(order);
    }

    // Cancel Order
    public void cancelOrder(Long orderId, Long userId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!order.getUser().getId().equals(userId)) {
            throw new RuntimeException("User not authorized to cancel this order");
        }

        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }

    // method to map Order to OrderResponse
    private OrderResponse toResponse(Order order) {
        return new OrderResponse(order.getId(), order.getUser().getId(), order.getProductIds(), order.getStatus(), order.getTotalAmount());
    }
}
