package com.lti.agro.dto;

import org.springframework.web.multipart.MultipartFile;

public class FarmerDto {
		
	
	MultipartFile aadhaarUpload;
	String aadharcardNumber;
	MultipartFile panCardUpload;
	MultipartFile certificateUpload;
	
	public String getAadharcardNumber() {
		return aadharcardNumber;
	}
	public void setAadharcardNumber(String aadharcardNumber) {
		this.aadharcardNumber = aadharcardNumber;
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
	public MultipartFile getCertificateUpload() {
		return certificateUpload;
	}
	public void setCertificateUpload(MultipartFile certificateUpload) {
		this.certificateUpload = certificateUpload;
	}
	
	
	
	
	
}
