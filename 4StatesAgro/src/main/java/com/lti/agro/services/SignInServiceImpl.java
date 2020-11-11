package com.lti.agro.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.agro.dto.LoginStatus;
import com.lti.agro.dto.Status.StatusType;
import com.lti.agro.entity.Bidder;
import com.lti.agro.entity.Farmer;
import com.lti.agro.repository.SignInDao;


@Service
public class SignInServiceImpl implements SignInService {
	
	@Autowired
	SignInDao signInDao;

	
	@Transactional
	public LoginStatus signIn(String email,String password,String userType){
		LoginStatus status = new LoginStatus();
		if(userType.compareTo("Admin")==0){// here we will add login credentails of admin
			if(email.compareTo("4statesagro@gmail.com")==0 && password.compareTo("Agro@2020")==0)
				status.setUserId(0);
			status.setName("Admin");
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("LoginSuccessFull");
				return status;
			
		}
		else if(userType.compareTo("Bidder")==0){
			
			try {
				Bidder bidder = signInDao.signInByBidder(email, password);
				status.setUserId(bidder.getbId());
				status.setName(bidder.getName());
				status.setStatus(StatusType.SUCCESS);
				status.setMessage("LoginSuccessFull");
				return status;
			} catch (Exception e) {
				status.setStatus(StatusType.FAILURE);
				status.setMessage("Email or Password is Incorrect");
				return status;
			}
			
			
		}
		else if(userType.compareTo("Farmer")==0){
			System.out.println("InsideFarmer");
			try {
				Farmer farmer = signInDao.signInByFarmer(email, password);
				status.setUserId(farmer.getfId());
				status.setName(farmer.getName());
				status.setStatus(StatusType.SUCCESS);
				status.setMessage("LoginSuccessFull");
				return status;
			} catch (Exception e) {
				status.setStatus(StatusType.FAILURE);
				status.setMessage("Email or Password is Incorrect");
				return status;
			}
		}
		status.setStatus(StatusType.FAILURE);
		status.setMessage("Login Failed!");
		return status;
	}
	
}
