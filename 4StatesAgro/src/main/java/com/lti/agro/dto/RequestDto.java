package com.lti.agro.dto;

public class RequestDto {
	int iD;
	public int getiD() {
		return iD;
	}


	public void setiD(int iD) {
		this.iD = iD;
	}


	String name; 
	String email;
	String PhoneNo;
	String bankAccount;
	String bankName;
	String ifscCode;
	String address;
	String city;
	String state;
	String pincode;
	String aadhaarCardNumber;
	String aadhaarUpload;
	String panCardNumber;
	String panCardUpload;
	String CertificateUpload;	//Farmer
	String password;
	int area; 			//Farmer hectre
	String landAddress; //Farmer
	String landstate;  //Farmer
	int landPincode;  //Farmer
	String certificateNumber; //Farmer
	
	String traderLicenseNumber; //Bidder 
	String traderLicenseUpload; //Bidder


	public String getTraderLicenseNumber() {
		return traderLicenseNumber;
	}


	public void setTraderLicenseNumber(String traderLicenseNumber) {
		this.traderLicenseNumber = traderLicenseNumber;
	}


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


	public String getPhoneNo() {
		return PhoneNo;
	}


	public void setPhoneNo(String phoneNo) {
		PhoneNo = phoneNo;
	}


	public String getBankAccount() {
		return bankAccount;
	}


	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}


	public String getBankName() {
		return bankName;
	}


	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


	public String getIfscCode() {
		return ifscCode;
	}


	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getPincode() {
		return pincode;
	}


	public void setPincode(String pincode) {
		this.pincode = pincode;
	}


	public String getAadhaarCardNumber() {
		return aadhaarCardNumber;
	}


	public void setAadhaarCardNumber(String aadhaarCardNumber) {
		this.aadhaarCardNumber = aadhaarCardNumber;
	}


	public String getAadhaarUpload() {
		return aadhaarUpload;
	}


	public void setAadhaarUpload(String aadhaarUpload) {
		this.aadhaarUpload = aadhaarUpload;
	}


	public String getPanCardNumber() {
		return panCardNumber;
	}


	public void setPanCardNumber(String panCardNumber) {
		this.panCardNumber = panCardNumber;
	}


	public String getPanCardUpload() {
		return panCardUpload;
	}


	public void setPanCardUpload(String panCardUpload) {
		this.panCardUpload = panCardUpload;
	}


	public String getCertificateUpload() {
		return CertificateUpload;
	}


	public void setCertificateUpload(String certificateUpload) {
		CertificateUpload = certificateUpload;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getArea() {
		return area;
	}


	public void setArea(int area) {
		this.area = area;
	}


	public String getLandAddress() {
		return landAddress;
	}


	public void setLandAddress(String landAddress) {
		this.landAddress = landAddress;
	}


	public String getLandstate() {
		return landstate;
	}


	public void setLandstate(String landstate) {
		this.landstate = landstate;
	}


	public int getLandPincode() {
		return landPincode;
	}


	public void setLandPincode(int landPincode) {
		this.landPincode = landPincode;
	}


	public String getCertificateNumber() {
		return certificateNumber;
	}


	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}


	public String getTraderLicenseUpload() {
		return traderLicenseUpload;
	}


	public void setTraderLicenseUpload(String traderLicenseUpload) {
		this.traderLicenseUpload = traderLicenseUpload;
	}
	
	
	
}
