package com.lti.agro.repository;

import com.lti.agro.entity.Bidder;
import com.lti.agro.entity.Farmer;

public interface SignInDao {
	
	public Farmer signInByFarmer(String email,String password);
	public Bidder signInByBidder(String email,String password);
	
}	
