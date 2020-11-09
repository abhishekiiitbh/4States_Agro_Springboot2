package com.lti.agro.repository;

import java.util.List;

import com.lti.agro.entity.Sales;

public interface SalesDao {
	public Sales addOrUpdateSales(Sales sale);
	public Sales findSalesById(int sId);
	public List<Sales> viewAllSales();
	public void findSalesByFarmerId(int fId);
	public List<Sales> findSalesByBidder(int bId);
}
