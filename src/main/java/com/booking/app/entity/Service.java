package com.booking.app.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Long id;

    @NotNull
    @Size(min = 3, max = 255, message = "Service name must be between 3 and 255 characters")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "service_type", nullable = false)
    private String serviceType; // e.g. "hotel", "flight", "car_rental", "event"

    @NotNull
    @DecimalMin(value = "0.01", message = "Price must be greater than zero")
    @Column(name = "price_per_unit", nullable = false)
    private BigDecimal pricePerUnit;  // The cost of the service (per night, per person, etc.)

    @Min(0)
    @Column(name = "available_units", nullable = false)
    private Integer availableUnits;  // Number of units available for booking (rooms, seats, etc.)

    @NotNull
    @Size(min = 2, max = 100, message = "Category name must be between 2 and 100 characters")
    @Column(name = "category", nullable = false)
    private String category;  // Category of the service (e.g. "luxury", "budget", "economy", "conference")

    @Column(name = "rating")
    @DecimalMin(value = "0.0", message = "Rating must be between 0 and 5")
    @DecimalMax(value = "5.0", message = "Rating must be between 0 and 5")
    private Double rating;  // Average rating of the service

    @NotNull
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;  // Indicates whether the service is active for booking

    @Column(name = "service_location")
    private String serviceLocation;  // Location of the service (e.g., city for hotels, airport for flights)

    @Column(name = "image_url")
    private String imageUrl;  // Image of the service (e.g., a hotel room or flight class)

    @Column(name = "provider_name")
    private String providerName;  // Name of the service provider (e.g., hotel chain, airline)

    @Column(name = "provider_rating")
    @DecimalMin(value = "0.0", message = "Provider rating must be between 0 and 5")
    @DecimalMax(value = "5.0", message = "Provider rating must be between 0 and 5")
    private Double providerRating;  // Rating of the service provider

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "service_start_date")
    private LocalDateTime serviceStartDate;  // Service start date (useful for events, flights, etc.)

    @Column(name = "service_end_date")
    private LocalDateTime serviceEndDate;  // Service end date (useful for events, rentals, etc.)

    @Column(name = "discount_price")
    private BigDecimal discountPrice;  // Discounted price (if applicable)

    @Transient
    public BigDecimal getDiscountPercentage() {
        if (pricePerUnit != null && discountPrice != null && pricePerUnit.compareTo(BigDecimal.ZERO) > 0) {
            return (pricePerUnit.subtract(discountPrice)).divide(pricePerUnit, 2, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100));
        }
        return BigDecimal.ZERO;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Integer getAvailableUnits() {
        return availableUnits;
    }

    public void setAvailableUnits(Integer availableUnits) {
        this.availableUnits = availableUnits;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getServiceLocation() {
        return serviceLocation;
    }

    public void setServiceLocation(String serviceLocation) {
        this.serviceLocation = serviceLocation;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public Double getProviderRating() {
        return providerRating;
    }

    public void setProviderRating(Double providerRating) {
        this.providerRating = providerRating;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getServiceStartDate() {
        return serviceStartDate;
    }

    public void setServiceStartDate(LocalDateTime serviceStartDate) {
        this.serviceStartDate = serviceStartDate;
    }

    public LocalDateTime getServiceEndDate() {
        return serviceEndDate;
    }

    public void setServiceEndDate(LocalDateTime serviceEndDate) {
        this.serviceEndDate = serviceEndDate;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }
}
