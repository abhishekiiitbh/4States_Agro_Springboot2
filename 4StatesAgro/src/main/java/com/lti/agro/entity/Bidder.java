package com.lti.agro.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "agro_bidder")
public class Bidder {
//public class Bidder  extends User {
	
	@Id
	@SequenceGenerator(name="bidSeq", initialValue=2001, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="bidSeq")
	int bId;//pk
	
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
	String traderLicenseUpload;
	String password;
	boolean approval;// "YES" OR "NO"
	/* upload-Aadhaar card, pan card,trader license*/
	
	String traderLicenseNumber;
	
	@OneToMany(mappedBy="bidder",cascade=CascadeType.ALL)
	List<Sales> sales;
	
	
	
	
	
	public boolean isApproval() {
		return approval;
	}
	public void setApproval(boolean approval) {
		this.approval = approval;
	}
	public String getAadhaarUpload() {
		return aadhaarUpload;
	}
	public void setAadhaarUpload(String aadhaarUpload) {
		this.aadhaarUpload = aadhaarUpload;
	}
	public String getPanCardUpload() {
		return panCardUpload;
	}
	public void setPanCardUpload(String panCardUpload) {
		this.panCardUpload = panCardUpload;
	}
	public String getTraderLicenseUpload() {
		return traderLicenseUpload;
	}
	public void setTraderLicenseUpload(String traderLicenseUpload) {
		this.traderLicenseUpload = traderLicenseUpload;
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
	public String getPanCardNumber() {
		return panCardNumber;
	}
	public void setPanCardNumber(String panCardNumber) {
		this.panCardNumber = panCardNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Sales> getSales() {
		return sales;
	}
	public void setSales(List<Sales> sales) {
		this.sales = sales;
	}
	public int getbId() {
		return bId;
	}
	public void setbId(int bId) {
		this.bId = bId;
	}
	public String getTraderLicenseNumber() {
		return traderLicenseNumber;
	}
	public void setTraderLicenseNumber(String traderLicenseNumber) {
		this.traderLicenseNumber = traderLicenseNumber;
	}


	
	
}
