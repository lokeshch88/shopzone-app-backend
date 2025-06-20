package com.shopzone.app.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopzone.app.dto.EmailRequest;
import com.shopzone.app.dto.OrderRequest;
import com.shopzone.app.dto.OrderResponse;
import com.shopzone.app.entity.Order;
import com.shopzone.app.entity.OrderStatus;
import com.shopzone.app.entity.User;
import com.shopzone.app.repo.OrderRepository;
import com.shopzone.app.repo.UserRepo;
import com.shopzone.utils.RandomCodeUtil;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepo userRepository;
    
    @Autowired
	private EmailService emailService;


    public OrderResponse createOrder(Long userId, OrderRequest request) {
    	
    	try {
        Order order = new Order();
        System.out.println("in order create service with user id "+userId);
        order.setUser(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));
        order.setProductIds(request.getProductIds()); 
        order.setStatus(OrderStatus.PENDING); // default status
        order.setTotalAmount(request.getTotalAmount());
        
        //generate uuid orderid
        String orderId=RandomCodeUtil.generateOrderId();
        System.out.println("Order id generated "+ orderId);
       order.setOrderId(orderId);
        
        Order savedOrder = orderRepository.save(order);
        return toResponse(savedOrder);
    	}catch (Exception e) {
    		e.printStackTrace();
			// TODO: handle exception
    		return null;
		}
    	}


    public OrderResponse updateOrderStatus(String orderId, OrderStatus status, Long userId) {
        Order order = orderRepository.findByOrderId(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        
        EmailRequest req=new EmailRequest();
        
        String subj="Order confirmed "+orderId;
      
        //fetch email id
       
       Optional<User> user= userRepository.findById(userId);
        String email=user.get().getEmail() ;
        String username=user.get().getFirstName() ;
        req.setSubject(subj);
        String message = "Hello "+username +",\n\n"
	               + "Your order "+status+" \n"
	               + "Shop with us again.";
        req.setMessage(message);
        req.setEmail(email);
        String resp=emailService.sendOrderConfirmedEmail(req);
        
        if("Notification email sent.".equals(resp)) {
        	System.out.println("order placed email sent");
        }
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
        return new OrderResponse(order.getOrderId(), order.getUser().getId(), order.getProductIds(), order.getStatus(), order.getTotalAmount());
    }
    
    private boolean proceedInWorkflow() {
		return true;
    	
    }
}
