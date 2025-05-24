package com.shopzone.app.dto;

import com.shopzone.app.entity.OrderStatus;

import java.util.List;

public class OrderResponse {

    private Long orderId;
    private Long userId;
    private List<Long> productIds;
    private OrderStatus status;
    private Double totalAmount;

    public OrderResponse() {}

    public OrderResponse(Long orderId, Long userId, List<Long> productIds, OrderStatus status, Double totalAmount) {
        this.orderId = orderId;
        this.userId = userId;
        this.productIds = productIds;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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
