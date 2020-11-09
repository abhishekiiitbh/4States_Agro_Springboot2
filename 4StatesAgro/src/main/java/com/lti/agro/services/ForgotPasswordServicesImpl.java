package com.lti.agro.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.agro.entity.Bidder;
import com.lti.agro.entity.Farmer;
import com.lti.agro.repository.BidderDaoImpl;
import com.lti.agro.repository.FarmerDaoImpl;

@Service
public class ForgotPasswordServicesImpl implements ForgotPasswordServices {
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	FarmerDaoImpl farmerDao;
	
	@Autowired
	BidderDaoImpl bidderDao;
	
	static Farmer farmer; 
	
	static Bidder bidder;
 	
	static String OTP;
	
	public boolean forgotPasswordEmailCheck(String email,String userType)
	{
		if(userType.compareTo("Farmer")==0)
		{
			try {
				System.out.println("Inside");
				farmer=farmerDao.findFarmerByEmaiId(email);
				String generator = UUID.randomUUID().toString();
				String generated[] = generator.split("-");
				OTP = generated[3];
				String text="Your OTP to reset Password is: "+OTP;
				String subject = "OTP!! FORGOT PASSWORD!!";
				emailService.sendEmailForNewRegistration(farmer.getEmail(), text, subject);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return false;
			}
			return true;
		}
		
		else if(userType.compareTo("Bidder")==0)
		{
			try {
				bidder=bidderDao.findBidderByEmaiId(email);
				String generator = UUID.randomUUID().toString();
				String generated[] = generator.split("-");
				OTP = generated[3];
				String text="Your OTP to reset Password is: "+OTP;
				String subject = "OTP!! FORGOT PASSWORD!!";
				emailService.sendEmailForNewRegistration(bidder.getEmail(), text, subject);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return false;
			}
			return true;
		}
		return false;
	}
	
	public boolean resetPassword(String otp,String newPassword,String userType) {
		if(userType.compareTo("Farmer")==0&&otp.compareTo(OTP)==0)
		{
			farmer.setPassword(newPassword);
			farmerDao.addOrUpdate(farmer);
			return true;
		}
		else if(userType.compareTo("Bidder")==0&&otp.compareTo(OTP)==0) {
			bidder.setPassword(newPassword);
			bidderDao.addOrUpfateBidder(bidder);
			return true;
		}
		return false;
	}
}	
