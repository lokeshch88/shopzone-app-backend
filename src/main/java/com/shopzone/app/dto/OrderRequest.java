package com.shopzone.app.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class OrderRequest {
	
	
    private LocalDateTime orderDateTime;
 
        private List<Long> productIds;
        private Double totalAmount;

        
        
        public OrderRequest() {}

        public List<Long> getProductIds() {
            return productIds;
        }

        public void setProductIds(List<Long> productIds) {
            this.productIds = productIds;
        }

        public Double getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(Double totalAmount) {
            this.totalAmount = totalAmount;
        }

		public LocalDateTime getOrderDateTime() {
			return orderDateTime;
		}

		public void setOrderDateTime(LocalDateTime orderDateTime) {
			this.orderDateTime = orderDateTime;
		}
   

  
	
	
    
    
}

