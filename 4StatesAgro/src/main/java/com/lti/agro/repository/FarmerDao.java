package com.lti.agro.repository;

import java.util.List;

import com.lti.agro.entity.Farmer;

public interface FarmerDao {
	public Farmer addOrUpdate(Farmer farmer);
	public Farmer findFarmerById(int farmerId);
	public Farmer findFarmerByAadharNo(String aadharNo);
	public List<Farmer> viewAllFarmers();
	public Farmer findFarmerByEmaiId(String email);
}
