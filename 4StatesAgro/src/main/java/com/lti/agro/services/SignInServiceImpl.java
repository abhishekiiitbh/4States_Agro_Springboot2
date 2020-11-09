package com.lti.agro.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.lti.agro.entity.Bidder;
import com.lti.agro.entity.Farmer;
import com.lti.agro.repository.SignInDaoImpl;


@Service
public class SignInServiceImpl {
	
	@Autowired
	SignInDaoImpl signInDao;

	
	@Transactional
	public boolean signIn(String email,String password,String userType){
		
		if(userType.compareTo("Admin")==0){// here we will add login credentails of admin
			if(email.compareTo("Abhishek.Pandit@lntinfotech.com")==0 && password.compareTo("Agro@2020")==0)
				return true;
			
		}
		else if(userType.compareTo("Bidder")==0){
			
			try {
				Bidder bidder = signInDao.signInByBidder(email, password);
				return true;
			} catch (Exception e) {
				return false;
			}
			
			
		}
		else if(userType.compareTo("Farmer")==0){
			System.out.println("InsideFarmer");
			try {
				Farmer farmer = signInDao.signInByFarmer(email, password);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}
	
}
