package com.lti.agro.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.agro.dto.SalesViewDto;
import com.lti.agro.entity.Bidder;
import com.lti.agro.entity.Sales;
import com.lti.agro.repository.BidderDao;
import com.lti.agro.repository.SalesDao;


@Service
public class BidderServicesImpl implements BidderServices {
	
	@Autowired
	BidderDao bidderDaoImpl;
	
	@Autowired
	SalesDao salesDaoImpl;
	
	public int addOrUpdateABidder(Bidder bidder) {
		
		try {
			bidderDaoImpl.findBidderByAadharCardNo(bidder.getAadhaarCardNumber());
			return 0;
		}catch(Exception e){
			Bidder newbidder = bidderDaoImpl.addOrUpfateBidder(bidder);
			return newbidder.getbId();
		}
		
	}
	
	public List<Sales> purchaseHistory(int bId)
	{
		List<Sales> sales = salesDaoImpl.findSalesByBidder(bId);
		List<Sales> bidderSales = new ArrayList<Sales>();
		for(Sales sale:sales)
		{
			if(sale.isCropSold()&&sale.getTransactionId().compareTo(null)!=0)
			{
				bidderSales.add(sale);
			}
		}
		return bidderSales;
	}
	
	public List<SalesViewDto> viewWonAuction(int bId)
	{
		List<Sales> sales = salesDaoImpl.findSalesByBidder(bId);
		List<SalesViewDto> bidderSales = new ArrayList<SalesViewDto>();
		for(Sales sale:sales)
		{
			if(sale.isCropSold()&&sale.getTransactionId()==null)
			{
				SalesViewDto salewon=new SalesViewDto();
				salewon.setSalesId(sale.getSalesId());
				salewon.setCropName(sale.getCropName());
				salewon.setBasePrice(sale.getBasePrice());
				salewon.setBiddingAmount(sale.getBiddingAmount());
				salewon.setFarmerName(sale.getFarmer().getName());
				salewon.setQuantity(sale.getQuantity());
				bidderSales.add(salewon);
			}
		}
		return bidderSales;
	}
	
	public boolean makePayment(int sId) {
		Sales sale = salesDaoImpl.findSalesById(sId);
		String transactionId = UUID.randomUUID().toString();
		sale.setTransactionId(transactionId);
		salesDaoImpl.addOrUpdateSales(sale);
		return true;
	}
	
	


	public boolean placeBid(int sId, double biddingAmount,int bId) {
		Sales sales=salesDaoImpl.findSalesById(sId);
		if(sales.getBiddingAmount()<biddingAmount&&sales.getBasePrice()<biddingAmount) {
			sales.setBiddingAmount(biddingAmount);
			Bidder bidder=bidderDaoImpl.findBidderById(bId);
			sales.setBidder(bidder);
			List<Sales> list=new ArrayList<Sales>();
			list.add(sales);
			bidder.setSales(list);
			salesDaoImpl.addOrUpdateSales(sales);
			return true;
		}
		return false;
		
	}

	@Override
	public int updateABidder(Bidder bidder) {
		Bidder newBidder = bidderDaoImpl.addOrUpfateBidder(bidder);
		return newBidder.getbId();
	}
	@Override
	public Bidder findBidderById(int bid) {
		return bidderDaoImpl.findBidderById(bid);
	}
}
