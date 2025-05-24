package com.booking.app.entity;

import java.awt.TextArea;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "service_provider")
public class ServiceProvider {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @Column(nullable = false, length = 255)
	    private String name;
	    
	    @Column(length = 50, nullable = false)
	    private String type;
	    
	    @Column(length = 500)
	    private String description;
	    
	    @Column(length = 255)
	    private String contact;
	    
	    private BigDecimal rating;
	    
	    @Column(name = "created_at", updatable = false)
		private LocalDateTime createdAt;
	    
		 @Column(name = "updated_at")
		private LocalDateTime updatedAt;
		 
		 @Column(name = "service_timing")
		private String serviceTiming;
		
		 @Column(name = "location_link")
		 private String locationLink;
		 private String address;
		 private String city;
		 private String pincode;
		 
		 @Column(name = "no_of_branches") 
		 private int noOfBranches;
		 
		 private boolean isMainBranch;
		 
		 
}
