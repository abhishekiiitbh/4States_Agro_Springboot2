package com.lti.agro.repository;

import java.util.List;

import com.lti.agro.entity.InsuranceApplications;



public interface InsuranceApplicationDao {
	
	public int ApplyForInsurance(InsuranceApplications insurance);
	public InsuranceApplications findInsurnaceByPolicyNo(int insuranceId);
	public InsuranceApplications findInsuranceByAadhar(String aadharNo);
	public List<InsuranceApplications> viewAllInsuranceApplications();
	public List<InsuranceApplications> showPreviousInsuranceById(int farmerid);
	public InsuranceApplications checkPolicyExists(int fId,int year);
}
