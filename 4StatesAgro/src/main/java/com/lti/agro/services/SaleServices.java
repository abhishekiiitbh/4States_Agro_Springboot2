package com.lti.agro.services;

import java.util.List;

import com.lti.agro.dto.SalesViewDto;

public interface SaleServices {
	public List<SalesViewDto> viewSoldCropHistory(int fId);
	public List<SalesViewDto> viewAllSales();
}
