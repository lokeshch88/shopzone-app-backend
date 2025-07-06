package com.shopzone.app.entity;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String addressLine;
    private String city;
    private String state;
    private String country;
    private String pincode;
    private String type;
    
    @Column(name = "is_primary_address")
    private String isPrimaryAddress;

    private Double latitude;
    private Double longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    @Column(name = "created_at", updatable = false)
   	private LocalDateTime createdAt;
   	 
   	 @Column(name = "updated_at")
   	private LocalDateTime updatedAt;

    public Address() {}


    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getAddressLine() { return addressLine; }

    public void setAddressLine(String addressLine) { this.addressLine = addressLine; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }

    public void setState(String state) { this.state = state; }

    public String getCountry() { return country; }

    public void setCountry(String country) { this.country = country; }

    public String getPincode() { return pincode; }

    public void setPincode(String pincode) { this.pincode = pincode; }

    public Double getLatitude() { return latitude; }

    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }

    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }
}

