package com.lti.agro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.agro.entity.InsuranceApplications;
import com.lti.agro.repository.InsuranceApplicationDao;


@Service
public class InsuranceApplicationServiceImpl implements InsuranceApplicationService {

	@Autowired
	InsuranceApplicationDao insuranceappndao;
	
	public void applyForInsurance(InsuranceApplications insurance) {
		
		int newPolicyNo = insuranceappndao.ApplyForInsurance(insurance);
		if(newPolicyNo>0)
		{
			System.out.println("Your Request has been submitted Successfully!");
		}
		
		else {
			System.out.println("Your Request has been Rejected!");
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

}
