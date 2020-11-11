package com.lti.agro.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.agro.dto.AdminViewInsuranceApplicationDto;
import com.lti.agro.dto.InsuranceClaimDto;
import com.lti.agro.dto.RequestDto;
import com.lti.agro.entity.Bidder;
import com.lti.agro.entity.Farmer;
import com.lti.agro.entity.InsuranceApplications;
import com.lti.agro.entity.InsuranceClaim;
import com.lti.agro.entity.Sales;
import com.lti.agro.repository.BidderDao;
import com.lti.agro.repository.FarmerDao;
import com.lti.agro.repository.InsuranceApplicationDao;
import com.lti.agro.repository.InsuranceClaimDao;
import com.lti.agro.repository.SalesDao;


@Service
public class AdminServicesImpl implements AdminServices{
	
	@Autowired
	FarmerDao farmerdao;
	
	@Autowired
	BidderDao bidderDao;
	
	@Autowired
	SalesDao salesDaoImpl;
	
	@Autowired
	InsuranceApplicationDao insuranceDao;
	
	@Autowired
	InsuranceClaimDao insuranceClaimDao;
	
	public Farmer approveFarmerRegistration(int fId) {
		Farmer newFarmer = farmerdao.findFarmerById(fId);
		if(newFarmer==null)
			return null;
		newFarmer.setApproval(true);
		farmerdao.addOrUpdate(newFarmer);
		return newFarmer;
	}
	
	public Sales approveSellRequest(int salesId,double basePrice) {
		Sales newSale=salesDaoImpl.findSalesById(salesId);
		if(newSale==null)
			return null;
		newSale.setBasePrice(basePrice);
		LocalDate startDate=LocalDate.now();
		newSale.setSaleStartDate(startDate);
		newSale.setSaleEndDate(startDate.plusDays(3));
		newSale.setCheckRequest(true);
		salesDaoImpl.addOrUpdateSales(newSale);
		
		return newSale;
	}
	
	
		public Sales finalizeBid(int sId) {
			
			Sales sales=salesDaoImpl.findSalesById(sId);
			if(sales==null) {
				return null;
			}
			LocalDate endDate=sales.getSaleEndDate();
			LocalDate today=LocalDate.of(2020, 11, 12);
			System.out.println("SalesEnddate:"+endDate);
			if(endDate.compareTo(today)==0) {
				sales.setCropSold(true);
				salesDaoImpl.addOrUpdateSales(sales);
				System.out.println("Sales endDate:::"+sales.getSaleEndDate());
			}
			return sales;
		}
		
		public Bidder approveBidderRegistration(int bId) {
			
			Bidder newBidder=bidderDao.findBidderById(bId);
			if(newBidder==null)
				return null;
			newBidder.setApproval(true);
			bidderDao.addOrUpfateBidder(newBidder);
			return newBidder;
				
				
			}
		
		public List<AdminViewInsuranceApplicationDto> viewRequest() {
			
			
			List<AdminViewInsuranceApplicationDto> napp=new ArrayList<>();
			
		    List<InsuranceApplications> app= insuranceDao.viewAllInsuranceApplications();
		    for(InsuranceApplications a:app) {
		    	if(a.isStatus()==false) {
		    		AdminViewInsuranceApplicationDto dto=new AdminViewInsuranceApplicationDto();
		    		dto.setName(a.getName());
		    		dto.setAddress(a.getAddress());
		    		dto.setCropImage1(a.getCropImage1());
		    		dto.setCropImage2(a.getCropImage2());
		    		dto.setCropType(a.getCropType());
		    		dto.setCropName(a.getCropName());
		    		dto.setCultivationArea(a.getCultivationArea());
		    		dto.setEmail(a.getEmail());
		    		dto.setFarmersPrimium(a.getFarmersPrimium());
		    		dto.setGovtsPrimium(a.getFarmersPrimium());
		    		//dto.setInsuranceComapnyName(a.getInsurancecompany().getCompanyName());
		    		dto.setPolicyNo(a.getPolicyNo());
		    		dto.setState(a.getState());
		    		dto.setYear(a.getYear());
		    		dto.setSumAssured(a.getSumAssured());
		    		napp.add(dto);
		    	}
		    }
		    return napp;
			
		}
		
		public boolean AdminApprovalForInsuranceApplication(int policyNo) {
			
			InsuranceApplications newapp=insuranceDao.findInsurnaceByPolicyNo(policyNo);
			if(newapp!= null) {
				newapp.setStatus(true);
				insuranceDao.ApplyForInsurance(newapp);
				return true;
			}
			return false;
		}
		
		public boolean AdminApprovalForInsuranceClaim(int rId,double amountClaim) {
			
			InsuranceClaim newclaim=insuranceClaimDao.findByRId(rId);
			if(newclaim!=null) {
				newclaim.setDateOfApproval(LocalDate.now());
				newclaim.setAmountClaimed(amountClaim);
				newclaim.setTransactionId(UUID.randomUUID().toString());
				insuranceClaimDao.placeAClaimRequest(newclaim);
				return true;
			}
			return false;
		}
		
		public List<RequestDto> viewFarmerRequests() {
			
			List<RequestDto> farmers = new ArrayList<>();
			List<Farmer> requests = farmerdao.viewAllFarmers();
			for(Farmer f:requests)
			{
				if(!f.isApproval())
				{	
					
					RequestDto request=new RequestDto();
					request.setiD(f.getfId());
					request.setName(f.getName());
					request.setEmail(f.getEmail());
					request.setPhoneNo(f.getPhoneNo());
					request.setBankAccount(f.getBankAccount());
					request.setBankName(f.getBankName());
					request.setIfscCode(f.getIfscCode());
					request.setAddress(f.getAddress());
					request.setCity(f.getCity());
					request.setState(f.getState());
					request.setPincode(f.getPincode());
					request.setAadhaarCardNumber(f.getAadhaarCardNumber());
					request.setAadhaarUpload(f.getAadhaarUpload());
					request.setPanCardNumber(f.getPanCardNumber());
					request.setPanCardUpload(f.getPanCardUpload());
					request.setCertificateUpload(f.getCertificateUpload());
					request.setPassword(f.getPassword());
					request.setArea(f.getArea());
					request.setLandAddress(f.getLandAddress());
					request.setLandstate(f.getLandstate());
					request.setLandPincode(f.getLandPincode());
					request.setCertificateNumber(f.getCertificateNumber());
					farmers.add(request);
				}
			}
			
			return farmers;
		}
		
		
		public List<RequestDto> viewBidderRequests() {
			
			List<RequestDto> bidders = new ArrayList<>();
			List<Bidder> requests = bidderDao.viewAllBidders();
			for(Bidder f:requests)
			{
				if(!f.isApproval())
				{	

					RequestDto request=new RequestDto();
					request.setiD(f.getbId());
					request.setName(f.getName());
					request.setEmail(f.getEmail());
					request.setPhoneNo(f.getPhoneNo());
					request.setBankAccount(f.getBankAccount());
					request.setBankName(f.getBankName());
					request.setIfscCode(f.getIfscCode());
					request.setAddress(f.getAddress());
					request.setCity(f.getCity());
					request.setState(f.getState());
					request.setPincode(f.getPincode());
					request.setAadhaarCardNumber(f.getAadhaarCardNumber());
					request.setAadhaarUpload(f.getAadhaarUpload());
					request.setPanCardNumber(f.getPanCardNumber());
					request.setPanCardUpload(f.getPanCardUpload());
					request.setTraderLicenseUpload(f.getTraderLicenseUpload());
					request.setTraderLicenseNumber(f.getTraderLicenseNumber());
					bidders.add(request);
				}
			}
			
			return bidders;
		}

		@Override
		public List<InsuranceClaimDto> viewClaimRequests() {
			List<InsuranceClaimDto> requests= new ArrayList<>();
			List<InsuranceClaim> applications = insuranceClaimDao.viewAllClaimRequests();
			for(InsuranceClaim ic:applications)
			{
				InsuranceClaimDto app=new InsuranceClaimDto();
				app.setCauseOfClaim(ic.getCauseOfClaim());
				app.setDateOfClaim(ic.getDateOfClaim());
				app.setDateOfLoss(ic.getDateOfLoss());
				app.setPolicyNo(ic.getInsuranceapplications().getPolicyNo());
				app.setrId(ic.getrId());
				requests.add(app);
			}
			return requests;
		}
}
