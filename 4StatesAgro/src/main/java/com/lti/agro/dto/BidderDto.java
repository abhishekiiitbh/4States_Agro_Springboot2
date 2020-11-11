package com.lti.agro.dto;

import org.springframework.web.multipart.MultipartFile;

public class BidderDto {
	String bId;
	MultipartFile traderLicenseUpload;
	MultipartFile aadhaarUpload;
	MultipartFile panCardUpload;
	
	public String getbId() {
		return bId;
	}
	public void setbId(String bId) {
		this.bId = bId;
	}
	public MultipartFile getTraderLicenseUpload() {
		return traderLicenseUpload;
	}
	public void setTraderLicenseUpload(MultipartFile traderLicenseUpload) {
		this.traderLicenseUpload = traderLicenseUpload;
	}
	public MultipartFile getAadhaarUpload() {
		return aadhaarUpload;
	}
	public void setAadhaarUpload(MultipartFile aadhaarUpload) {
		this.aadhaarUpload = aadhaarUpload;
	}
	public MultipartFile getPanCardUpload() {
		return panCardUpload;
	}
	public void setPanCardUpload(MultipartFile panCardUpload) {
		this.panCardUpload = panCardUpload;
	}
	
	
}
