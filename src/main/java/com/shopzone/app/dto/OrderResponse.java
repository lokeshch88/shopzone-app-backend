package com.shopzone.app.dto;

import com.shopzone.app.entity.OrderStatus;

import java.util.List;

public class OrderResponse {

    private String id;
    private Long userId;
    private List<Long> productIds;
    private OrderStatus status;
    private Double totalAmount;

    public OrderResponse() {}

    public OrderResponse(String orderId, Long userId, List<Long> productIds, OrderStatus status, Double totalAmount) {
        this.id = orderId;
        this.userId = userId;
        this.productIds = productIds;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    public String getOrderId() {
        return id;
    }

    public void setOrderId(String id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
