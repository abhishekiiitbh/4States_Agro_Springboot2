package com.lti.agro.dto;

import java.time.LocalDate;

public class SaleRequestDto {
		
	String cropName;
	String cropType;
	int quantity;
	String soilPhCertificate;
	String fertilizer;
	String cropImage1;
	String cropImage2;
	int farmerId;
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

	public int getFarmerId() {
		return farmerId;
	}
	public void setFarmerId(int farmerId) {
		this.farmerId = farmerId;
	}
	
	
}	
