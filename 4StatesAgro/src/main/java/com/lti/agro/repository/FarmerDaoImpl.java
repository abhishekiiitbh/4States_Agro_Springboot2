package com.lti.agro.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lti.agro.entity.Farmer;



@Repository
public class FarmerDaoImpl {
	
	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public Farmer addOrUpdate(Farmer farmer) {
		return em.merge(farmer);
	}
	
	public Farmer findFarmerById(int farmerId) {
		
		Farmer foundFarmer = em.find(Farmer.class, farmerId);
		
		return foundFarmer;
		
	}
	
	public Farmer findFarmerByAadharNo(String aadharNo) {
		
		String jpql = "Select f from Farmer f where f.aadhaarCardNumber=:aadhar";
		TypedQuery<Farmer> query= em.createQuery(jpql,Farmer.class);
		query.setParameter("aadhar", aadharNo);
		Farmer foundFarmer = query.getSingleResult();
		return foundFarmer;
		
	}
	
	public List<Farmer> viewAllFarmers(){
		String jpql= "Select f from Farmer f";
		TypedQuery<Farmer> query = em.createQuery(jpql,Farmer.class);
		return query.getResultList();
		
	}
}
