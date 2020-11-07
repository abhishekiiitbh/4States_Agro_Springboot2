package com.lti.agro.dto;

import java.time.LocalDate;

public class InsuranceClaimDto {
	
	String causeOfClaim;
	LocalDate dateOfLoss;
	LocalDate dateOfClaim;
	
	String cropImage1;
	String cropImage2;
	
	int policyNo;
	public String getCauseOfClaim() {
		return causeOfClaim;
	}
	public void setCauseOfClaim(String causeOfClaim) {
		this.causeOfClaim = causeOfClaim;
	}
	public LocalDate getDateOfLoss() {
		return dateOfLoss;
	}
	public void setDateOfLoss(LocalDate dateOfLoss) {
		this.dateOfLoss = dateOfLoss;
	}
	public LocalDate getDateOfClaim() {
		return dateOfClaim;
	}
	public void setDateOfClaim(LocalDate dateOfClaim) {
		this.dateOfClaim = dateOfClaim;
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
	
	public int getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(int policyNo) {
		this.policyNo = policyNo;
	}

}
