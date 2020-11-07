package com.lti.agro.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "agro_insuranceapplication")
public class InsuranceApplications {
	
	
	String name;
	String email;
	String address;
	String cropName;
	String cropType;
	int cultivationArea;
	String cropImage1;
	String cropImage2;
	int year;
	double sumAssured;//cultivationArea*sumAssured
	double farmersPrimium;//sumAssursed*farmerInterestRate/100
	double govtsPrimium;//sumAssured-farmerPrimium
	String State;
	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	@Id
	@SequenceGenerator(name="insappSeq", initialValue=9001, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="insappSeq")
	int policyNo;
	
	boolean status;
	
	@OneToOne
	@JoinColumn(name="fId")
	Farmer farmer;
	
	@ManyToOne
	@JoinColumn(name="companyId")
	InsuranceCompanies insurancecompany;
	
	@OneToOne(mappedBy="insuranceapplication",cascade=CascadeType.ALL)
	InsuranceClaim insuranceclaim;
	
	
	


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

	public InsuranceCompanies getInsurancecompany() {
		return insurancecompany;
	}

	public void setInsurancecompany(InsuranceCompanies insurancecompany) {
		this.insurancecompany = insurancecompany;
	}

	public InsuranceClaim getInsuranceclaim() {
		return insuranceclaim;
	}

	public void setInsuranceclaim(InsuranceClaim insuranceclaim) {
		this.insuranceclaim = insuranceclaim;
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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getSumAssured() {
		return sumAssured;
	}

	public void setSumAssured(double sumAssured) {
		this.sumAssured = sumAssured;
	}

	public double getFarmersPrimium() {
		return farmersPrimium;
	}

	public void setFarmersPrimium(double farmersPrimium) {
		this.farmersPrimium = farmersPrimium;
	}

	public double getGovtsPrimium() {
		return govtsPrimium;
	}

	public void setGovtsPrimium(double govtsPrimium) {
		this.govtsPrimium = govtsPrimium;
	}

	public int getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(int policyNo) {
		this.policyNo = policyNo;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Farmer getFarmer() {
		return farmer;
	}

	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}

	@Override
	public String toString() {
		return "InsuranceApplications [name=" + name + ", email=" + email + ", address=" + address + ", cropName="
				+ cropName + ", cropType=" + cropType + ", cultivationArea=" + cultivationArea + ", year=" + year
				+ ", sumAssured=" + sumAssured + ", farmersPrimium=" + farmersPrimium + ", govtsPrimium=" + govtsPrimium
				+ ", policyNo=" + policyNo + ", status=" + status + ", farmer=" + farmer + ", insurancecompany="
				+ insurancecompany + ", insuranceclaim=" + insuranceclaim + "]";
	}
	
	
	
	
	
	
	
}
