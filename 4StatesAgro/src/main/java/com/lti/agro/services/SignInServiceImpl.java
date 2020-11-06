package com.lti.agro.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lti.agro.entity.Bidder;
import com.lti.agro.entity.Farmer;


@Repository
public class SignInServiceImpl {
	
	@PersistenceContext
	EntityManager em;

	
	@Transactional
	public boolean signIn(String email,String password,String userType){
		
		if(userType=="Admin"){// here we will add login credentails of admin
			if(email=="Abhishek.Pandit@lntinfotech.com" && password=="Agro@2020")
				return true;
			
		}else if(userType=="Bidder"){
			
			String jpql="select b from Bidder b where b.email=:eml and b.password=:pwd";
			// here we will check approval if it is "Yes" then check login credentails
			
			Query query=em.createQuery(jpql,Bidder.class);
			query.setParameter("eml", email);
			query.setParameter("pwd", password);
			
			Bidder acc=(Bidder)query.getResultList().stream().findFirst().orElse(null);
	        if(acc!=null && acc.isApproval()==true)
	            return true;
			
			
		}else if(userType=="Farmer"){
			
			String jpql="select f from tbl_farmer b where f.email=:eml and f.password=:pwd";
			Query query=em.createQuery(jpql,Farmer.class);
			query.setParameter("eml", email);
			query.setParameter("pwd", password);
			
			Farmer acc=(Farmer)query.getResultList().stream().findFirst().orElse(null);
	        if(acc!=null)
	            return true;
			
		}
		
		
		return false;
	}
	
}
