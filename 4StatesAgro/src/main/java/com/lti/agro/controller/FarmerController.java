package com.lti.agro.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.agro.dto.SaleRequestDto;
import com.lti.agro.dto.SalesViewDto;
import com.lti.agro.dto.Status;
import com.lti.agro.dto.Status.StatusType;
import com.lti.agro.entity.Farmer;
import com.lti.agro.entity.Sales;
import com.lti.agro.repository.FarmerDaoImpl;
import com.lti.agro.repository.SalesDaoImpl;
import com.lti.agro.services.EmailService;
import com.lti.agro.services.FarmerServices;

@CrossOrigin
@RestController
public class FarmerController {
	
	@Autowired
	FarmerServices farmerService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	SalesDaoImpl salesdaotest;
	
	@PostMapping(path="/registerFarmer")
	public Status registerFarmer(@RequestBody Farmer farmer){
		
		farmer.setApproval(false);
		boolean result =  farmerService.addOrUpdate(farmer);
		Status status = new Status();
		if(result)
		{
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Registration Request Submitted Successfully!");
			String text="Your Request for Registration has been Submitted Successfully! Please await for"
						+" confirmation mail from our Team!"+"Regards!<br>4StatesAgro ";
			
			String subject = "Registration Confirmation";
			emailService.sendEmailForNewRegistration(farmer.getEmail(), text, subject);
			
		}
		else
		{
			status.setStatus(StatusType.FAILURE);
			status.setMessage("Request Failed! Your Record Already exists!");
		}
		return status;
	}
	
	@PostMapping(path = "/placeASellRequest")
	public Status placeASellRequest(@RequestBody SaleRequestDto saleRequest) {
		
		
		Sales sales = new Sales();
		sales.setCropName(saleRequest.getCropName());
		sales.setCropType(saleRequest.getCropType());
		sales.setQuantity(saleRequest.getQuantity());
		sales.setFertilizer(saleRequest.getFertilizer());
		sales.setSoilPhCertificate(saleRequest.getSoilPhCertificate());
		sales.setCropImage1(saleRequest.getCropImage1());
		sales.setCropImage2(saleRequest.getCropImage2());
		int farmerId= saleRequest.getFarmerId();
		
		boolean newsale = farmerService.placeASellRequest(sales,farmerId);

		Status status = new Status();
		if(newsale)
		{
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Sell Request Placed Successfully!");
		}
		else
		{
			status.setStatus(StatusType.FAILURE);
			status.setMessage("Sell Request didn't get placed successfully!!");
		}
		return status;
	}
	
	@GetMapping(path = "/viewMarketPlaceFarmer")
	public List<SalesViewDto> viewMarketPlace(@RequestParam("fId") int fId) {

		return farmerService.viewAllSales(fId);

	
	}
	
}
