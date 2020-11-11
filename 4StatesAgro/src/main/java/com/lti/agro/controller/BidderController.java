package com.lti.agro.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.agro.dto.BidderDto;
import com.lti.agro.dto.FarmerDto;
import com.lti.agro.dto.SalesViewDto;
import com.lti.agro.dto.Status;
import com.lti.agro.dto.Status.StatusType;
import com.lti.agro.entity.Bidder;
import com.lti.agro.entity.Farmer;
import com.lti.agro.entity.Sales;
import com.lti.agro.services.BidderServices;
import com.lti.agro.services.EmailService;

@CrossOrigin
@RestController
public class BidderController {
	
	@Autowired
	BidderServices bidderServices;
	@Autowired
	private EmailService emailServices;
	
	@PostMapping(path="/registerBidder")
	public Status registerBidder(@RequestBody Bidder bidder){
		
		bidder.setApproval(false);
		int res=bidderServices.addOrUpdateABidder(bidder);
		Status status=new Status();
		if(res>0) {
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("You have successfully generated a registration request!");
			String text="Your registration request is submitted successfully, please await for confirmation mail.";
			String subject="Registration Confirmation";
			status.setId(res);
			emailServices.sendEmailForNewRegistration(bidder.getEmail(), text, subject);
		}else {
			status.setStatus(StatusType.FAILURE);
			status.setMessage("Registration failed! Your records already exists");
			status.setId(0);
		}
		return status;
	}
	
	@PostMapping(path = "/bidderDocumentUploads")
	public Status uploadDocuments(BidderDto bidder) {
		Status status = new Status();
		Bidder newBidder = bidderServices.findBidderById(Integer.parseInt(bidder.getbId()));
		 String aadharLocation ="D:/uploads/Farmer/Aadhar/";
			String aadharName = bidder.getAadhaarUpload().getOriginalFilename();
			String aadharFile = aadharLocation +aadharName;
			try {
				FileCopyUtils.copy(bidder.getAadhaarUpload().getInputStream(), new FileOutputStream(aadharFile));
			}
			catch (IOException e) {
	            e.printStackTrace();
	            status.setStatus(StatusType.FAILURE);
	            status.setMessage(e.getMessage());
	            return status;
			}
			String panCardLocation ="D:/uploads/Farmer/Pan/";
			String panCardName = bidder.getPanCardUpload().getOriginalFilename();
			String panCardFile = panCardLocation+panCardName;
			try {
				FileCopyUtils.copy(bidder.getPanCardUpload().getInputStream(), new FileOutputStream(panCardFile));
			}
			catch (IOException e) {
	            e.printStackTrace();
	            status.setStatus(StatusType.FAILURE);
	            status.setMessage(e.getMessage());
	            return status;
			}
			String licenseLocation ="D:/uploads/Farmer/Certificate/";
			String licenseName = bidder.getTraderLicenseUpload().getOriginalFilename();
			String licenseFile = licenseLocation+licenseName;
			try {
				FileCopyUtils.copy(bidder.getTraderLicenseUpload().getInputStream(), new FileOutputStream(licenseFile));
			}
			catch (IOException e) {
	            e.printStackTrace();
	            status.setStatus(StatusType.FAILURE);
	            status.setMessage(e.getMessage());
	            return status;
			}
		System.out.println(aadharFile);
		System.out.println(panCardFile);
		System.out.println(licenseFile);
		newBidder.setAadhaarUpload(aadharFile);
		newBidder.setPanCardUpload(panCardFile);
		newBidder.setTraderLicenseUpload(licenseFile);
		int result = bidderServices.updateABidder(newBidder);
		if(result>0) {
		status.setStatus(StatusType.SUCCESS);
        status.setMessage("Uploaded Successfully!");
		}
		else
		{
			status.setStatus(StatusType.FAILURE);
	        status.setMessage("Uploaded Failed!");
		}
        return status;
		
	}
	
	@GetMapping(path = "/purchaseHistory")
	public List<Sales> viewPurchaseHistiory(@RequestParam("bId") int bId){
		return bidderServices.purchaseHistory(bId);
	}
	
	
	@GetMapping(path = "/viewWonAuction")
	public List<SalesViewDto> viewWonAuction(@RequestParam("bId") int bId)
	{
		return bidderServices.viewWonAuction(bId);
	}
	
	
	
	@PostMapping(path = "/makePayment")
	public Status makePayment(@RequestParam("sId") int sId)
	{
		boolean result = bidderServices.makePayment(sId);
		Status status = new Status();
		if(result)
		{
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Payment Successfull!");
		}
		else
		{
			status.setStatus(StatusType.FAILURE);
			status.setMessage("Payment Failed!!");
		}
		return status;
	}
	
	@PostMapping(path="/make-bid")
	public Status makeBidOnProduct(@RequestParam("sId") int sId,@RequestParam("bidAmount")double bidAmount,@RequestParam("bId")int bId) {
		 boolean result = bidderServices.placeBid(sId, bidAmount, bId);
		 Status status=new Status();
		 if(result) {
		 status.setStatus(StatusType.SUCCESS);
		 status.setMessage("Bid Was Successful");
		 }
		 else
		 {
			 status.setStatus(StatusType.FAILURE);
			 status.setMessage("Bid Was UnSuccessful");
		 }
		 return status;
		 //System.out.println("Made SuccessFully");
	}
	
}