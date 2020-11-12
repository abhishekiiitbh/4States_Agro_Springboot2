package com.lti.agro.dto;

import org.springframework.web.multipart.MultipartFile;

public class ClaimDocumentDto {
	
	String rId;
	MultipartFile cropImage1;
	MultipartFile cropImage2;
	
	public String getrId() {
		return rId;
	}
	public void setrId(String rId) {
		this.rId = rId;
	}
	public MultipartFile getCropImage1() {
		return cropImage1;
	}
	public void setCropImage1(MultipartFile cropImage1) {
		this.cropImage1 = cropImage1;
	}
	public MultipartFile getCropImage2() {
		return cropImage2;
	}
	public void setCropImage2(MultipartFile cropImage2) {
		this.cropImage2 = cropImage2;
	}
	
}
