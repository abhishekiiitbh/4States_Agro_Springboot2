package com.lti.agro.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lti.agro.entity.InsuranceCompanies;

@Repository
public class InsuranceCompanyImpl {
	
@PersistenceContext
EntityManager em;
@Transactional

public InsuranceCompanies addOrUpdateCompany(InsuranceCompanies company){
	return em.merge(company);
		
}
public InsuranceCompanies findCompanyById(int companyId) {
	return em.find(InsuranceCompanies.class, companyId);
}

public InsuranceCompanies findCompanyByStateAndCropType(String State,String cropType) {
	String jpql="select c from InsuranceCompanies c where c.State=:st and c.cropType=:ct";
	TypedQuery<InsuranceCompanies> query=em.createQuery(jpql, InsuranceCompanies.class);
	query.setParameter("st", State);
	query.setParameter("ct", cropType);
	return query.getSingleResult();
	
}
}

