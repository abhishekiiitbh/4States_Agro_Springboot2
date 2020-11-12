package com.lti.agro.services;

import java.util.List;

import com.lti.agro.dto.SalesViewDto;
import com.lti.agro.entity.Sales;

public interface SaleServices {
	public List<SalesViewDto> viewSoldCropHistory(int fId);
	public List<SalesViewDto> viewAllSales();
	public SalesViewDto findSalesById(int salesId);
	public Sales findSaleById(int salesId);
	public List<SalesViewDto> viewSellRequest();
	public boolean updateSales(Sales sale);
}
