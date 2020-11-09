package com.lti.agro.services;

import java.util.List;

import com.lti.agro.dto.CalculateDto;
import com.lti.agro.dto.InsuranceViewDto;
import com.lti.agro.entity.InsuranceApplications;

public interface InsuranceApplicationService {
	public boolean  applyForInsurance(InsuranceApplications insuranec,int fId);
	public InsuranceApplications findInsurnceByPolicyNo(int policyNo);
	public void viewAllInsuranceApplications();
	public void findInsuranceByAadharNo(String aadharNo);
	public List<InsuranceViewDto> showPreviousInsuranceById(int fid);
	public CalculateDto calcInsurance(String state,String cropType,int area);
}
