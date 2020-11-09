package com.lti.agro.services;

import java.util.List;

import com.lti.agro.dto.SalesViewDto;
import com.lti.agro.entity.Farmer;
import com.lti.agro.entity.Sales;

public interface FarmerServices {
	public boolean addOrUpdate(Farmer farmer);
	public boolean placeASellRequest(Sales sale,int farmerId);
	public List<SalesViewDto> viewAllSales(int fId);
	
}
