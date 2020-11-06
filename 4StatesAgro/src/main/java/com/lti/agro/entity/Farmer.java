package com.lti.agro.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="agro_farmer")
public class Farmer {
//public class Farmer extends User {
	
	@Id
	@SequenceGenerator(name="farmSeq", initialValue=1001, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="farmSeq")
	int fId;// pk
	
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
	String CertificateUpload;
	String password;
	boolean approval;// "YES" OR "NO"
	
	/* upload-Aadhaar card, pan card,certificate */
	
	
	int area;// hectares
	String landAddress;
	String landstate;
	int landPincode;
	String certificateNumber;
	
	
	
	@OneToMany(mappedBy="farmer",cascade=CascadeType.ALL)
	List<Sales> sales;
	
	@OneToOne(mappedBy="farmer",cascade=CascadeType.ALL)
	InsuranceApplications insuranceapplication;
	
	
	
	
	
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
	public String getCertificateUpload() {
		return CertificateUpload;
	}
	public void setCertificateUpload(String certificateUpload) {
		CertificateUpload = certificateUpload;
	}
	public boolean isApproval() {
		return approval;
	}
	public void setApproval(boolean approval) {
		this.approval = approval;
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
	public String getLandstate() {
		return landstate;
	}
	public void setLandstate(String landstate) {
		this.landstate = landstate;
	}
	public InsuranceApplications getInsuranceapplication() {
		return insuranceapplication;
	}
	public void setInsuranceapplication(InsuranceApplications insuranceapplication) {
		this.insuranceapplication = insuranceapplication;
	}
	public List<Sales> getSales() {
		return sales;
	}
	public void setSales(List<Sales> sales) {
		this.sales = sales;
	}
	public int getfId() {
		return fId;
	}
	public void setfId(int fId) {
		this.fId = fId;
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
	
	@Override
	public String toString() {
		return "Farmer [fId=" + fId + ", name=" + name + ", email=" + email + ", PhoneNo=" + PhoneNo + ", bankAccount="
				+ bankAccount + ", bankName=" + bankName + ", ifscCode=" + ifscCode + ", address=" + address + ", city="
				+ city + ", state=" + state + ", pincode=" + pincode + ", aadhaarCardNumber=" + aadhaarCardNumber
				+ ", panCardNumber=" + panCardNumber + ", password=" + password + ", approval=" + approval + ", area="
				+ area + ", landAddress=" + landAddress + ", landstate=" + landstate + ", landPincode=" + landPincode
				+ ", certificateNumber=" + certificateNumber  + "]";
	}
	
	
	
}
