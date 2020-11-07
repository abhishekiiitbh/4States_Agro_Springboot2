package com.lti.agro;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lti.agro.entity.InsuranceCompanies;
import com.lti.agro.repository.InsuranceCompanyImpl;

@SpringBootTest
class ApplicationTests {

	@Autowired
	InsuranceCompanyImpl insuranceCompanyImpl;
	@Test
	void findCompanyByStateAndCropType() {
		
		InsuranceCompanies company=
		insuranceCompanyImpl.findCompanyByStateAndCropType("MAHARASTRA", "RABI");
		System.out.println(company.getCompanyName());
	}

}
