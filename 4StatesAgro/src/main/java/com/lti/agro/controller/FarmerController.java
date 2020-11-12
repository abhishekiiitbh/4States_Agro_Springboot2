package com.lti.agro.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.agro.dto.FarmerDto;
import com.lti.agro.dto.SaleRequestDto;
import com.lti.agro.dto.SalesDocumentDto;
import com.lti.agro.dto.SalesViewDto;
import com.lti.agro.dto.Status;
import com.lti.agro.dto.Status.StatusType;
import com.lti.agro.entity.Farmer;
import com.lti.agro.entity.Sales;
import com.lti.agro.repository.SalesDao;
import com.lti.agro.services.EmailService;
import com.lti.agro.services.FarmerServices;
import com.lti.agro.services.SaleServices;

@CrossOrigin
@RestController
public class FarmerController {
	
	@Autowired
	FarmerServices farmerService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	SaleServices saleservice;
	
	@PostMapping(path="/registerFarmer")
	public Status registerFarmer(@RequestBody Farmer farmer){
		Status status = new Status();
		
       
		int fId =  farmerService.addOrUpdate(farmer);
		if(fId>0)
		{
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Registration Request Submitted Successfully!");
			status.setId(fId);
			String text="Your Request for Registration has been Submitted Successfully! Please await for"
						+" confirmation mail from our Team!"+"Regards!<br>4StatesAgro ";
			
			String subject = "Registration Confirmation";
			emailService.sendEmailForNewRegistration(farmer.getEmail(), text, subject);
			
		}
		else
		{
			status.setStatus(StatusType.FAILURE);
			status.setMessage("Request Failed! Your Record Already exists!");
			status.setId(fId);
		}
		return status;
	}
	
	@PostMapping("/farmerDocumentUploads")
	public Status uploadDocuments(FarmerDto farmer) {
		Status status = new Status();
		Farmer newFarmer = farmerService.findFarmerById(Integer.parseInt(farmer.getAadharcardNumber()));
		 String aadharLocation ="D:/uploads/Farmer/Aadhar/";
			String aadharName = farmer.getAadhaarUpload().getOriginalFilename();
			String aadharFile = aadharLocation +aadharName;
			try {
				FileCopyUtils.copy(farmer.getAadhaarUpload().getInputStream(), new FileOutputStream(aadharFile));
			}
			catch (IOException e) {
	            e.printStackTrace();
	            status.setStatus(StatusType.FAILURE);
	            status.setMessage(e.getMessage());
	            return status;
			}
			String panCardLocation ="D:/uploads/Farmer/Pan/";
			String panCardName = farmer.getPanCardUpload().getOriginalFilename();
			String panCardFile = panCardLocation+panCardName;
			try {
				FileCopyUtils.copy(farmer.getPanCardUpload().getInputStream(), new FileOutputStream(panCardFile));
			}
			catch (IOException e) {
	            e.printStackTrace();
	            status.setStatus(StatusType.FAILURE);
	            status.setMessage(e.getMessage());
	            return status;
			}
			String certificateLocation ="D:/uploads/Farmer/Certificate/";
			String certificateName = farmer.getCertificateUpload().getOriginalFilename();
			String certificateFile = certificateLocation+certificateName;
			try {
				FileCopyUtils.copy(farmer.getCertificateUpload().getInputStream(), new FileOutputStream(certificateFile));
			}
			catch (IOException e) {
	            e.printStackTrace();
	            status.setStatus(StatusType.FAILURE);
	            status.setMessage(e.getMessage());
	            return status;
			}
		System.out.println(aadharName);
		System.out.println(panCardName);
		System.out.println(certificateName);
		newFarmer.setAadhaarUpload(aadharName);
		newFarmer.setPanCardUpload(panCardName);
		newFarmer.setCertificateUpload(certificateName);
		int result = farmerService.updateFarmer(newFarmer);
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
		
		int newsale = farmerService.placeASellRequest(sales,farmerId);

		Status status = new Status();
		if(newsale>0)
		{
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Sell Request Placed Successfully!");
			status.setId(newsale);
		}
		else
		{
			status.setStatus(StatusType.FAILURE);
			status.setMessage("Sell Request didn't get placed successfully!!");
			status.setId(0);
		}
		return status;
	}
	
	
	
	@GetMapping(path = "/viewMarketPlaceFarmer")
	public List<SalesViewDto> viewMarketPlace(@RequestParam("fId") int fId) {

		return farmerService.viewAllSales(fId);

	
	}
	
	@GetMapping(path = "/retrieveFarmerDocuments")
	public Farmer viewDocuments(@RequestParam("fId") int id,HttpServletRequest request)
	{
		Farmer farmer = farmerService.findFarmerById(id);
		String projPath = request.getServletContext().getRealPath("/");
		String tempDownloadPath = projPath +"/downloads/";
		File f = new File(tempDownloadPath);
		if(!f.exists())
			f.mkdir();
		
		String aadharSourceLocation="D:/uploads/Farmer/Aadhar/";
		String panCardSourceLocation = "D:/uploads/Farmer/Pan/";
		
		
		
		String aadhartargetFile = tempDownloadPath + farmer.getAadhaarUpload();
		System.out.println(aadhartargetFile);
		String panCardtargetFile = tempDownloadPath + farmer.getPanCardUpload();
		System.out.println(panCardtargetFile);
		String aadharsourceFile =  aadharSourceLocation + farmer.getAadhaarUpload();
		System.out.println(aadharsourceFile);
		String panCardsourceFile = panCardSourceLocation + farmer.getPanCardUpload();
		System.out.println(panCardsourceFile);
		try {
			FileCopyUtils.copy(new File(aadharsourceFile), new File(aadhartargetFile));
			FileCopyUtils.copy(new File(panCardsourceFile), new File(panCardtargetFile));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		farmer.setSales(null);
		farmer.setInsuranceapplication(null);
		return farmer;
	}
	
}
