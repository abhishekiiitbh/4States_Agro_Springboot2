package com.lti.agro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.agro.dto.Status;
import com.lti.agro.dto.Status.StatusType;
import com.lti.agro.entity.InsuranceCompanies;
import com.lti.agro.repository.InsuranceCompanyDao;

@RestController
@CrossOrigin
public class InsuranceCompanyController {

	@Autowired
	InsuranceCompanyDao insurancecom;
	@PostMapping(path="/registerCompany")
	
	public Status AddAInsuranceCompany(@RequestBody InsuranceCompanies companies) {
		
		insurancecom.addOrUpdateCompany(companies);
		Status status=new Status();
		status.setStatus(StatusType.SUCCESS);
		status.setMessage("Insurance request submitted successfully");
		return status;
		
	}
	
}
