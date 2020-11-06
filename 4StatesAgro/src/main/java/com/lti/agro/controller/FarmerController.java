package com.lti.agro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.agro.entity.Farmer;
import com.lti.agro.repository.FarmerDaoImpl;

@CrossOrigin
@RestController
public class FarmerController {
	
	@Autowired
	FarmerDaoImpl farmerdaotest;
	
	@PostMapping(path="/registerFarmer")
	public String registerFarmer(@RequestBody Farmer farmer){
		
		Farmer newfarmer=farmerdaotest.addOrUpdate(farmer);
		
		System.out.println(newfarmer.getfId()+" "+newfarmer.getAadhaarCardNumber()+newfarmer.getName());
		
		return "registration successful.";
		
	}
	
	
}
