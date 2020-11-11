package com.lti.agro.dto;

import java.time.LocalDate;

public class SalesViewDto {
	String cropName;
	String cropType;
	int quantity;
	String soilPhCertificate;
	String fertilizer;
	double basePrice;
	double biddingAmount;
	String cropImage1;
	String cropImage2;
	LocalDate saleStartDate;
	LocalDate saleEndDate;
	String farmerName;
	
	String state;
	int salesId;
	
	public int getSalesId() {
		return salesId;
	}
	public void setSalesId(int salesId) {
		this.salesId = salesId;
	}
	public String getCropName() {
		return cropName;
	}
	public void setCropName(String cropName) {
		this.cropName = cropName;
	}
	public String getCropType() {
		return cropType;
	}
	public void setCropType(String cropType) {
		this.cropType = cropType;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getSoilPhCertificate() {
		return soilPhCertificate;
	}
	public void setSoilPhCertificate(String soilPhCertificate) {
		this.soilPhCertificate = soilPhCertificate;
	}
	public String getFertilizer() {
		return fertilizer;
	}
	public void setFertilizer(String fertilizer) {
		this.fertilizer = fertilizer;
	}

	public double getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	public double getBiddingAmount() {
		return biddingAmount;
	}
	public void setBiddingAmount(double biddingAmount) {
		this.biddingAmount = biddingAmount;
	}
	public String getCropImage1() {
		return cropImage1;
	}
	public void setCropImage1(String cropImage1) {
		this.cropImage1 = cropImage1;
	}
	public String getCropImage2() {
		return cropImage2;
	}
	public void setCropImage2(String cropImage2) {
		this.cropImage2 = cropImage2;
	}
	public LocalDate getSaleStartDate() {
		return saleStartDate;
	}
	public void setSaleStartDate(LocalDate saleStartDate) {
		this.saleStartDate = saleStartDate;
	}
	public LocalDate getSaleEndDate() {
		return saleEndDate;
	}
	public void setSaleEndDate(LocalDate saleEndDate) {
		this.saleEndDate = saleEndDate;
	}
	public String getFarmerName() {
		return farmerName;
	}
	public void setFarmerName(String farmerName) {
		this.farmerName = farmerName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
