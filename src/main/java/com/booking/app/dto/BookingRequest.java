package com.booking.app.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingRequest {
    private Long productId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    
    private LocalDateTime bookingDateTime;
    private String bookingSlotCode;
    
    
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	public LocalDateTime getBookingDateTime() {
		return bookingDateTime;
	}
	public void setBookingDateTime(LocalDateTime bookingDateTime) {
		this.bookingDateTime = bookingDateTime;
	}
	public String getBookingSlotCode() {
		return bookingSlotCode;
	}
	public void setBookingSlotCode(String bookingSlotCode) {
		this.bookingSlotCode = bookingSlotCode;
	}
	
	
	
    
    
}

