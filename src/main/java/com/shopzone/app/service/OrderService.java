package com.shopzone.app.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.shopzone.app.controller.CategoryController;
import com.shopzone.app.dto.EmailRequest;
import com.shopzone.app.dto.OrderItemDto;
import com.shopzone.app.dto.OrderRequest;
import com.shopzone.app.dto.OrderResponse;
import com.shopzone.app.entity.Order;
import com.shopzone.app.entity.OrderItem;
import com.shopzone.app.entity.OrderStatus;
import com.shopzone.app.entity.Product;
import com.shopzone.app.entity.User;
import com.shopzone.app.repo.OrderRepository;
import com.shopzone.app.repo.ProductRepository;
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

	@Autowired
	private ProductRepository productRepository;

	private static final Logger log = LoggerFactory.getLogger(OrderService.class);

	@Autowired
	private ModelMapper modelMapper;

	public OrderResponse createOrder(Long userId, OrderRequest request) {

		try {
			Order order = new Order();
			log.info("In order create service with user id " + userId);
			order.setUser(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));

			order.setStatus(OrderStatus.PENDING); // default status
			order.setTotalAmount(request.getTotalAmount());
			order.setCreatedAt(LocalDateTime.now());
			// generate uuid orderid
			String orderId = RandomCodeUtil.generateOrderId();
			log.info("Order id generated " + orderId);
			order.setOrderId(orderId);

			log.info("Order items received " + request.getItems().toString());
			List<OrderItem> orderItems = new ArrayList<>();
			for (OrderItemDto dto : request.getItems()) {
				Product product = productRepository.findById(dto.getProductId())
						.orElseThrow(() -> new RuntimeException("Product not found"));
				OrderItem item = new OrderItem();
				item.setOrder(order);
				item.setProduct(product);
				item.setSize(dto.getSize());
				item.setColor(dto.getColor());
				item.setPrice(dto.getPrice());
				item.setQuantity(dto.getQuantity());
				orderItems.add(item);
			}

			order.setItems(orderItems);

			Order savedOrder = orderRepository.save(order);
			return toResponse(savedOrder);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return null;
		}
	}

	public OrderResponse updateOrderStatus(String orderId, OrderStatus status, Long userId) {
		Order order = orderRepository.findByOrderId(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
		order.setStatus(status);
		order.setUpdatedAt(LocalDateTime.now());
		EmailRequest req = new EmailRequest();

		String subj = "Order " + status + orderId;

		// fetch email id

		Optional<User> user = userRepository.findById(userId);
		String email = user.get().getEmail();
		String username = user.get().getFirstName();
		req.setSubject(subj);
		String message = "Hello " + username + ",\n\n" + "Your order " + status + " \n" + "Shop with us again.";
		req.setMessage(message);
		req.setEmail(email);
		String resp = emailService.sendOrderConfirmedEmail(req);

		if ("Notification email sent.".equals(resp)) {
			System.out.println("order placed email sent");
		}
		return toResponse(orderRepository.save(order));
	}

	public List<OrderResponse> getOrdersForUser(Long userId) {
		List<Order> orders = orderRepository.findByUserId(userId);

		return orders.stream().map(order -> {
			OrderResponse dto = new OrderResponse();
			dto.setOrderId(order.getOrderId());
			dto.setUserId(order.getUser().getId());
			dto.setTotalAmount(order.getTotalAmount());
			dto.setCreatedAt(order.getCreatedAt());
			// Map order items to item DTOs
			List<OrderItemDto> itemDtos = order.getItems().stream().map(item -> {
				OrderItemDto itemDto = new OrderItemDto();
				itemDto.setProductId(item.getProduct().getId());
				itemDto.setSize(item.getSize());
				itemDto.setColor(item.getColor());
				itemDto.setPrice(item.getPrice());
				itemDto.setQuantity(item.getQuantity());
				return itemDto;
			}).collect(Collectors.toList());

			dto.setItems(itemDtos);

			return dto;
		}).collect(Collectors.toList());
	}

	public List<OrderResponse> getAllOrders() {
		List<Order> orders = orderRepository.findAll();

		return orders.stream().map(order -> {
			OrderResponse dto = new OrderResponse();
			dto.setOrderId(order.getOrderId());
			dto.setUserId(order.getUser().getId());
			dto.setTotalAmount(order.getTotalAmount());
			dto.setStatus(order.getStatus());

			// Map order items to item DTOs
			List<OrderItemDto> itemDtos = order.getItems().stream().map(item -> {
				OrderItemDto itemDto = new OrderItemDto();
				itemDto.setProductId(item.getProduct().getId());
				itemDto.setSize(item.getSize());
				itemDto.setColor(item.getColor());
				itemDto.setPrice(item.getPrice());
				itemDto.setQuantity(item.getQuantity());
				return itemDto;
			}).collect(Collectors.toList());

			dto.setItems(itemDtos);

			return dto;
		}).collect(Collectors.toList());
	}

	// by ID
	public OrderResponse getOrderById(String id) {
		Order order = orderRepository.findByOrderId(id).orElseThrow(() -> new RuntimeException("Order not found"));
		return modelMapper.map(order, OrderResponse.class);
	}

	// Cancel Order
	public void cancelOrder(Long orderId, Long userId) {
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

		if (!order.getUser().getId().equals(userId)) {
			throw new RuntimeException("User not authorized to cancel this order");
		}

		order.setStatus(OrderStatus.CANCELLED);
		orderRepository.save(order);
	}

	// method to map Order to OrderResponse
	private OrderResponse toResponse(Order order) {
		return new OrderResponse(order.getOrderId(), order.getUser().getId(), order.getStatus(),
				order.getTotalAmount());
	}

	private boolean proceedInWorkflow() {
		return true;

	}
}
