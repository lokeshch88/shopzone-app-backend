package com.shopzone.app.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coupon")
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	private String description;

	@Column(name = "valid_to", nullable = false)
	private LocalDate validTo;

	@Column(name = "valid_from", nullable = false)
	private LocalDate validFrom;

	@Column(name = "percentage_off")
	private int percentageOff;

	private Double amount;

	@Column(name = "min_order_value", nullable = false)
	private Double minOrderValue;

	@Column(name = "eligibility_criteria")
	private String eligibilityCriteria;

	@Column(name = "valid_categories")
	private String validCategories;

	@Column(name = "is_active", nullable = false)
	private String isActive;

	@Column(name = "create_at", nullable = false)
	private LocalDateTime createAt;

	@Column(name = "updated_at")
	private LocalDate updatedAt;

	@Column(name = "created_by", nullable = false)
	private String createdBy;

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

	public LocalDate getValidTo() {
		return validTo;
	}

	public void setValidTo(LocalDate validTo) {
		this.validTo = validTo;
	}

	public LocalDate getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(LocalDate validFrom) {
		this.validFrom = validFrom;
	}

	public int getPercentageOff() {
		return percentageOff;
	}

	public void setPercentageOff(int percentageOff) {
		this.percentageOff = percentageOff;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getMinOrderValue() {
		return minOrderValue;
	}

	public void setMinOrderValue(Double minOrderValue) {
		this.minOrderValue = minOrderValue;
	}

	public String getEligibilityCriteria() {
		return eligibilityCriteria;
	}

	public void setEligibilityCriteria(String eligibilityCriteria) {
		this.eligibilityCriteria = eligibilityCriteria;
	}

	public String getValidCategories() {
		return validCategories;
	}

	public void setValidCategories(String validCategories) {
		this.validCategories = validCategories;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", name=" + name + ", description=" + description + ", validTo=" + validTo
				+ ", validFrom=" + validFrom + ", percentageOff=" + percentageOff + ", amount=" + amount
				+ ", minOrderValue=" + minOrderValue + ", eligibilityCriteria=" + eligibilityCriteria
				+ ", validCategories=" + validCategories + ", isActive=" + isActive + ", createAt=" + createAt
				+ ", updatedAt=" + updatedAt + ", createdBy=" + createdBy + "]";
	}
	
	
	
	
}
