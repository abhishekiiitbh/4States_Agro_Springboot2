package com.lti.agro.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.agro.dto.SalesViewDto;
import com.lti.agro.entity.Sales;
import com.lti.agro.repository.SalesDaoImpl;

@Service
public class SaleServices {
	
	
	@Autowired
	SalesDaoImpl salesDaoImpl;
	
	public List<SalesViewDto> viewSoldCropHistory(int fId) {

		List<Sales> sales=salesDaoImpl.viewAllSales();

		List<SalesViewDto> res=new ArrayList<SalesViewDto>();

		for (Sales s : sales) {
			if (s.isCheckRequest() == true && s.isCropSold() == true && s.getFarmer().getfId()==fId) {
				SalesViewDto sale=new SalesViewDto();
				sale.setCropName(s.getCropName());
				sale.setCropType(s.getCropType());
				sale.setBasePrice(s.getBasePrice());
				sale.setBiddingAmount(s.getBiddingAmount());
				sale.setCropImage1(s.getCropImage1());
				sale.setCropImage2(s.getCropImage2());
				sale.setFarmerName(s.getFarmer().getName());
				sale.setState(s.getFarmer().getState());
				sale.setQuantity(s.getQuantity());
				sale.setFertilizer(s.getFertilizer());
				sale.setSaleEndDate(s.getSaleEndDate());
				sale.setSaleStartDate(s.getSaleStartDate());
				sale.setSoilPhCertificate(s.getSoilPhCertificate());
				res.add(sale);
			}
		}

		return res;
	}
	
	public List<SalesViewDto> viewAllSales(){
		List<Sales> sales=salesDaoImpl.viewAllSales();

		List<SalesViewDto> res=new ArrayList<SalesViewDto>();

		for (Sales s : sales) {
			if (s.isCheckRequest() == true && s.isCropSold() == false) {
				SalesViewDto sale=new SalesViewDto();
				sale.setCropName(s.getCropName());
				sale.setCropType(s.getCropType());
				sale.setBasePrice(s.getBasePrice());
				sale.setBiddingAmount(s.getBiddingAmount());
				sale.setCropImage1(s.getCropImage1());
				sale.setCropImage2(s.getCropImage2());
				sale.setFarmerName(s.getFarmer().getName());
				sale.setState(s.getFarmer().getState());
				sale.setQuantity(s.getQuantity());
				sale.setFertilizer(s.getFertilizer());
				sale.setSaleEndDate(s.getSaleEndDate());
				sale.setSaleStartDate(s.getSaleStartDate());
				sale.setSoilPhCertificate(s.getSoilPhCertificate());
				res.add(sale);
			}
		}

		return res;
	}
	
}