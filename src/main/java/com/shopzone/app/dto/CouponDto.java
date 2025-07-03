package com.shopzone.app.dto;

import java.time.LocalDate;

public class CouponDto {
	private String name;
	private String description;
	private LocalDate validTo;
	private LocalDate validFrom;
	private int percentageOff;
	private Double amount;
	private Double minOrderValue;
	private String eligibilityCriteria;

	private String validCategories;
	private String isActive;
	
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
	@Override
	public String toString() {
		return "CouponDto [name=" + name + ", description=" + description + ", validTo=" + validTo + ", validFrom="
				+ validFrom + ", percentageOff=" + percentageOff + ", amount=" + amount + ", minOrderValue="
				+ minOrderValue + ", eligibilityCriteria=" + eligibilityCriteria + ", validCategories="
				+ validCategories + ", isActive=" + isActive + "]";
	}

	
}
