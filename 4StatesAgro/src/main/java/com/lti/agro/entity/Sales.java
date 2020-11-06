package com.lti.agro.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "agro_sales")
public class Sales {
	
	@Id
	@SequenceGenerator(name = "saleSeq",initialValue = 3001,allocationSize = 1)
	@GeneratedValue(generator = "saleSeq",strategy = GenerationType.SEQUENCE)
	int salesId;
	
	String cropName;
	String cropType;
	int quantity;
	String soilPhCertificate;
	String fertilizer;
	boolean check;
	boolean sold;
	double basePrice;
	double biddingAmount;
	String transactionId;
	String cropImage1;
	String cropImage2;
	LocalDate saledate;
	
	@ManyToOne
	@JoinColumn(name="fId")
	Farmer farmer;
	
	@ManyToOne
	@JoinColumn(name="bId")
	Bidder bidder;
	
	
	
	public String getSoilPhCertificate() {
		return soilPhCertificate;
	}
	public void setSoilPhCertificate(String soilPhCertificate) {
		this.soilPhCertificate = soilPhCertificate;
	}
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
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
	public LocalDate getSaledate() {
		return saledate;
	}
	public void setSaledate(LocalDate saledate) {
		this.saledate = saledate;
	}
	public Farmer getFarmer() {
		return farmer;
	}
	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}
	public Bidder getBidder() {
		return bidder;
	}
	public void setBidder(Bidder bidder) {
		this.bidder = bidder;
	}
	public int getSalesId() {
		return salesId;
	}
	public void setSalesId(int salesId) {
		this.salesId = salesId;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getFertilizer() {
		return fertilizer;
	}
	public void setFertilizer(String fertilizer) {
		this.fertilizer = fertilizer;
	}
	public boolean isSold() {
		return sold;
	}
	public void setSold(boolean sold) {
		this.sold = sold;
	}
	public double getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	public double getBiddingAmount() {
		return biddingAmount;
	}
	public void setBiddingAmount(double biddingAmount) {
		this.biddingAmount = biddingAmount;
	}
	public LocalDate getDate() {
		return saledate;
	}
	public void setDate(LocalDate saledate) {
		this.saledate = saledate;
	}
	
	
}
