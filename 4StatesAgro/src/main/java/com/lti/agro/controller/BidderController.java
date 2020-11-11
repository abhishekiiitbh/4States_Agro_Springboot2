package com.lti.agro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.agro.dto.SalesViewDto;
import com.lti.agro.dto.Status;
import com.lti.agro.dto.Status.StatusType;
import com.lti.agro.entity.Bidder;
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
		boolean res=bidderServices.addOrUpdateABidder(bidder);
		Status status=new Status();
		if(res) {
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("You have successfully generated a registration request!");
			String text="Your registration request is submitted successfully, please await for confirmation mail.";
			String subject="Registration Confirmation";
			
			emailServices.sendEmailForNewRegistration(bidder.getEmail(), text, subject);
		}else {
			status.setStatus(StatusType.FAILURE);
			status.setMessage("Registration failed! Your records already exists");
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