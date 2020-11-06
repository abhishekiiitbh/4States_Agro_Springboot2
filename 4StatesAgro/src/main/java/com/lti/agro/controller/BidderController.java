package com.lti.agro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.agro.entity.Bidder;
import com.lti.agro.repository.BidderDaoImpl;

@CrossOrigin
@RestController
public class BidderController {
	
	@Autowired
	BidderDaoImpl bidderdaotest;
	@PostMapping(path="/registerBidder")
	public String registerFarmer(@RequestBody Bidder bidder){
		
		Bidder newbidder=bidderdaotest.addOrUpfateBidder(bidder);
		
		System.out.println(newbidder.getbId()+" "+newbidder.getAadhaarCardNumber()+newbidder.getName());
		
		return "registration successful.";
		
	}
}
