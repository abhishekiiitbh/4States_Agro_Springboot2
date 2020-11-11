package com.lti.agro.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.agro.dto.SalesViewDto;
import com.lti.agro.entity.Farmer;
import com.lti.agro.entity.Sales;
import com.lti.agro.repository.FarmerDao;
import com.lti.agro.repository.SalesDao;

@Service
public class FarmerServicesImpl implements FarmerServices {
	@Autowired
	EmailService emailService;
	
	@Autowired
	FarmerDao farmerdao;
	
	@Autowired
	SalesDao salesdao;
	
	public int addOrUpdate(Farmer farmer) {
		
		try {
			
			farmerdao.findFarmerByAadharNo(farmer.getAadhaarCardNumber());
			return 0;
		
		} catch (Exception  e) {
			
			Farmer newFarmer = farmerdao.addOrUpdate(farmer);
			return newFarmer.getfId();
		}
		
	}
	public boolean placeASellRequest(Sales sale,int farmerId) {
		
		Farmer record = farmerdao.findFarmerById(farmerId);
		if(record==null)
			return false;
		
		sale.setBasePrice(0);
		sale.setCheckRequest(false);
		sale.setCropSold(false);
		sale.setBidder(null);
		sale.setTransactionId(null);
		sale.setSaleStartDate(LocalDate.of(1, 1, 1));
		sale.setSaleEndDate(LocalDate.of(1, 1, 1));
		sale.setFarmer(record);
		salesdao.addOrUpdateSales(sale);
		
		String text="Your Request for Sale has been placed successfully! \n Please await for"
				+" confirmation mail from our Team for furher Quality Check Process!"+"\n Regards! \n 4StatesAgro ";
	
		String subject = "Registration Confirmation";
		emailService.sendEmailForNewRegistration(record.getEmail(), text, subject);
		return true;
	}
	
	public List<SalesViewDto> viewAllSales(int fId) {
		
		List<Sales> sales =salesdao.viewAllSales();
		List<SalesViewDto> res=new ArrayList<SalesViewDto>();

		for (Sales s : sales) {
			if (s.isCheckRequest() == true && s.isCropSold() == false && s.getFarmer().getfId()==fId) {
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
	
	public int updateFarmer(Farmer farmer) {
		Farmer newFarmer = farmerdao.addOrUpdate(farmer);
		return newFarmer.getfId();
	}
	
	@Override
	public Farmer findFarmerById(int fId) {
		try {
			return farmerdao.findFarmerById(fId);
		} catch (Exception e) {
			return null;
		}
	}
}
