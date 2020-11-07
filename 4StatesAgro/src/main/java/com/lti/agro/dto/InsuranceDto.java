package com.lti.agro.dto;

public class InsuranceDto {
	String name;
	String email;
	String address;
	String cropName;
	String cropType;
	int cultivationArea;
	String cropImage1;
	String cropImage2;
	int year;
    int farmerId;
    String state;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public int getCultivationArea() {
		return cultivationArea;
	}
	public void setCultivationArea(int cultivationArea) {
		this.cultivationArea = cultivationArea;
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
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getFarmerId() {
		return farmerId;
	}
	public void setFarmerId(int farmerId) {
		this.farmerId = farmerId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
