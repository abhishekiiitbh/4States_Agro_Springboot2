package com.lti.agro.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.agro.dto.CalculateDto;
import com.lti.agro.dto.InsuranceViewDto;
import com.lti.agro.entity.Farmer;
import com.lti.agro.entity.InsuranceApplications;
import com.lti.agro.entity.InsuranceCompanies;
import com.lti.agro.repository.FarmerDaoImpl;
import com.lti.agro.repository.InsuranceApplicationDao;
import com.lti.agro.repository.InsuranceCompanyImpl;


@Service
public class InsuranceApplicationServiceImpl implements InsuranceApplicationService {

	@Autowired
	InsuranceApplicationDao insuranceappndao;
	@Autowired
	FarmerDaoImpl farmerDao;
	@Autowired
	InsuranceCompanyImpl insuranceCompany;
	public boolean applyForInsurance(InsuranceApplications insurance,int fId) {
		 System.out.println(insurance.getState());
		 System.out.println(insurance.getCropType());
		 try {
			insuranceappndao.checkPolicyExists(fId, insurance.getYear());
			return false;
		} catch (Exception e) {
			System.out.println("No Records Found!");
		}
		 Farmer farmer=farmerDao.findFarmerById(fId); 
		 if (farmer==null)
			 return false;
		 insurance.setFarmer(farmer);
		 InsuranceCompanies companies=insuranceCompany.findCompanyByStateAndCropType(insurance.getState(), insurance.getCropType());
		 double sumAssured=companies.getSumAssuredPrHectare()*insurance.getCultivationArea();
		 insurance.setSumAssured(sumAssured);
		 insurance.setFarmersPrimium(sumAssured*companies.getFarmerInterestRate()/100);
		 insurance.setGovtsPrimium(sumAssured-insurance.getFarmersPrimium());
		 insurance.setInsurancecompany(companies);
		int newPolicyNo = insuranceappndao.ApplyForInsurance(insurance);
		if(newPolicyNo>0)
		{
			return true;
		}
		
		else {
			return false;
		}
		
	}

	public InsuranceApplications findInsurnceByPolicyNo(int policyNo) {
		
		InsuranceApplications foundInsurance = insuranceappndao.findInsurnaceByPolicyNo(policyNo);
		if(foundInsurance!=null)
		{
			System.out.println("Record Fetched Succefully!");
			System.out.println(foundInsurance);
			return foundInsurance;
		}
		
		else {
			System.out.println("No Record Founds!");
			return null;
		}
		
	}

	public void viewAllInsuranceApplications() {
		
		List<InsuranceApplications> applications=insuranceappndao.viewAllInsuranceApplications();
		for(InsuranceApplications application:applications)
		{
			System.out.println(application);
		}
		
	}

	public void findInsuranceByAadharNo(String aadharNo) {
		
		InsuranceApplications foundInsurance = insuranceappndao.findInsuranceByAadhar(aadharNo);
		if(foundInsurance!=null)
		{
			System.out.println("Record Fetched Succefully!");
			System.out.println(foundInsurance);
		}
		
		else {
			System.out.println("No Record Founds!");
		}
		
	}
	
	public List<InsuranceViewDto> showPreviousInsuranceById(int fid) {
		List<InsuranceApplications> ia=insuranceappndao.showPreviousInsuranceById(fid);
		List<InsuranceViewDto> views= new ArrayList<>();
		
		for(InsuranceApplications insurance:ia)
		{	
			
			InsuranceViewDto view=new InsuranceViewDto();
			view.setName(insurance.getName());
			view.setEmail(insurance.getEmail());
			view.setAddress(insurance.getAddress());
			view.setCropName(insurance.getCropName());
			view.setCropType(insurance.getCropType());
			view.setCultivationArea(insurance.getCultivationArea());
			view.setCropImage1(insurance.getCropImage1());
			view.setCropImage2(insurance.getCropImage2());
			view.setYear(insurance.getYear());
			view.setState(insurance.getState());
			view.setCompanyName(insurance.getCropName());
			view.setFarmerName(insurance.getFarmer().getName());
			view.setSumAssured(insurance.getSumAssured());
			view.setFarmerPremium(insurance.getFarmersPrimium());
			view.setGovtPremium(insurance.getGovtsPrimium());
			view.setInterestByCompany(insurance.getInsurancecompany().getInterest());
			view.setFarmersInterest(insurance.getInsurancecompany().getFarmerInterestRate());
			views.add(view);
		}
		return views;
	}
	
	public CalculateDto calcInsurance(String state,String cropType,int area) {
		InsuranceCompanies companies=insuranceCompany.findCompanyByStateAndCropType(state, cropType);
		
	   double sumAssured=area * companies.getSumAssuredPrHectare();
	   double farmerPremium=sumAssured* companies.getFarmerInterestRate()/100;
	   double govtsPremium=sumAssured - farmerPremium;
	  // System.out.println(sumAssured+" "+farmerPremium+" "+govtsPremium); 
	  CalculateDto calcDto=new CalculateDto();
	  calcDto.setSumAssured(sumAssured);
	  calcDto.setFarmersPremium(farmerPremium);
	  calcDto.setGovtsPremium(govtsPremium);
	  return calcDto;
	    
	}

}
