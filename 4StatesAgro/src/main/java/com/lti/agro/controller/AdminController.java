package com.lti.agro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.agro.dto.AdminViewInsuranceApplicationDto;
import com.lti.agro.dto.RequestDto;
import com.lti.agro.dto.Status;
import com.lti.agro.dto.Status.StatusType;
import com.lti.agro.entity.Bidder;
import com.lti.agro.entity.Farmer;
import com.lti.agro.entity.Sales;
import com.lti.agro.repository.ContactUsDaoImpl;
import com.lti.agro.services.AdminServicesImpl;
import com.lti.agro.services.EmailService;
import com.lti.agro.services.SignInServiceImpl;

@RestController
@CrossOrigin
public class AdminController {
	
	@Autowired
	AdminServicesImpl adminService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	ContactUsDaoImpl contactusdaoimpl;
	
	@Autowired
	SignInServiceImpl signin;
	
	@PostMapping(path = "/approveFarmerRegistration")
	public Status approveFarmerRegistration(@RequestParam("fid") int fId) {
		
		Farmer approvedFarmer = adminService.approveFarmerRegistration(fId);
		Status status = new Status();
		if(approvedFarmer!=null)
		{
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Farmer Profile Approved Successfully!");
			String text = "Your profile has been approved successfully. \n Your iD is:"+approvedFarmer.getfId();
			String subject = "Registration Aproved!";
			emailService.sendEmailForNewRegistration(approvedFarmer.getEmail(), text, subject);
		}
		else
		{
			status.setStatus(StatusType.FAILURE);
			status.setMessage("Farmer Profile Does Not Exist!");
		}
		return status;
	}
	//guna
	@PostMapping(path = "/finalizeBid")

	public Status finalizeBid(@RequestParam("sId") int sId) {
		Sales sales = adminService.finalizeBid(sId);
		Status status = new Status();
		if (sales != null) {
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Sales Closed!!!");
			// To farmer:

			String text = "Congratulations!!\nYour Crop:" + sales.getCropName() + "was Auctioned Successfully!!";
			String subject = "Crop Auction Successfully";
			emailService.sendEmailForNewRegistration(sales.getFarmer().getEmail(), text, subject);
			// To bidder:
			String bidder_text = "Congratulations!!\nYou are the Winner for crop:" + sales.getCropName();
			String bidder_subject = "Crop Auction Successfully";
			emailService.sendEmailForNewRegistration(sales.getBidder().getEmail(), bidder_text, bidder_subject);

		} else {
			status.setStatus(StatusType.FAILURE);
			status.setMessage("Sales Not Found!!!");
		}
		return status;
	}
	
	
	@PostMapping(path="/approveSellRequest")
	public Status approveSellRequest(@RequestParam("salesId") int salesId,@RequestParam("basePrice") double basePrice){
		
		Sales aprovedSellRequest=adminService.approveSellRequest(salesId, basePrice);
		Status status=new Status();
		if(aprovedSellRequest!=null)
		{
		status.setStatus(StatusType.SUCCESS);
		status.setMessage("Sell request approved.");
		String subject="Sell Request Approved!";
		String text="Your Crop has been approved successfully. \n Your Sales Id is: "+aprovedSellRequest.getSalesId();
		emailService.sendEmailForNewRegistration(aprovedSellRequest.getFarmer().getEmail(), text, subject);
		}
		else
		{
			status.setStatus(StatusType.FAILURE);
			status.setMessage("Your Sell request Does Not Exist!");
		}
		
		return status;
	}
	
	@PostMapping(path="/approveBidderRegistration")
	public Status approveBidderRegistration(@RequestParam("bid") int bId) {
		Bidder approvedBidder=adminService.approveBidderRegistration(bId);
		Status status=new Status();
		if(approvedBidder!=null) {
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Bidder porfile approved successfully");
			String text="Your profile has been approved!\n Your Id is "+approvedBidder.getbId();
			String subject="Registration Approved";
			
			emailService.sendEmailForNewRegistration(approvedBidder.getEmail(), text, subject);
		}else {
			status.setStatus(StatusType.FAILURE);
			status.setMessage("Bidder profile does not exist");
		}
		return status;
	}
	
	@GetMapping(path="/ViewInsuranceRequest")
	public List<AdminViewInsuranceApplicationDto> viewRequests(){
	return adminService.viewRequest();
	}
	
	@PostMapping(path="/ApproveInsurance")
	public Status approveApplication(@RequestParam("pNo") int policyNo) {
		boolean result=adminService.AdminApprovalForInsuranceApplication(policyNo);
		Status status=new Status();
		if(result) {
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Approved Successfully");
		}else {
			status.setStatus(StatusType.FAILURE);
			status.setMessage("Approval rejected");
		}
		return status;
		
	}
	
	@PostMapping(path = "/login")
	public Status login(@RequestParam("email") String email,@RequestParam("pwd") String password,@RequestParam("type") String userType) {
		System.out.println(email+ " "+password+" "+userType);
		boolean result = signin.signIn(email, password, userType);
		Status status=new Status();
		if(result) {
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Login Successfull");
		}else {
			status.setStatus(StatusType.FAILURE);
			status.setMessage("Login Failed");
		}
		return status;
	}
	
	@PostMapping(path="/ApproveInsuranceClaim")
	public Status approveClaim(@RequestParam("rId") int rId,@RequestParam("amount") double amountClaim) {
		boolean result=adminService.AdminApprovalForInsuranceClaim(rId,amountClaim);
		Status status=new Status();
		if(result) {
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Approved claim successfully");
		}else {
			status.setStatus(StatusType.FAILURE);
			status.setMessage("Claim rejected");
		}
		return status;
		
	}
	
	@GetMapping(path = "/viewFarmerRegistrationRequest")
	public List<RequestDto> viewFarmerRequests(){
		return adminService.viewFarmerRequests();
	}
	
	@GetMapping(path = "/viewBidderRegistrationRequest")
	public List<RequestDto> viewBidderRequests(){
		return adminService.viewBidderRequests();
	}
	
}
