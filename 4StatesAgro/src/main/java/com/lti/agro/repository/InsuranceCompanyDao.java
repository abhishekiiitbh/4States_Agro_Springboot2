package com.lti.agro.repository;

import com.lti.agro.entity.InsuranceCompanies;

public interface InsuranceCompanyDao {
	public InsuranceCompanies addOrUpdateCompany(InsuranceCompanies company);
	public InsuranceCompanies findCompanyById(int companyId);
	public InsuranceCompanies findCompanyByStateAndCropType(String State,String cropType);
}
