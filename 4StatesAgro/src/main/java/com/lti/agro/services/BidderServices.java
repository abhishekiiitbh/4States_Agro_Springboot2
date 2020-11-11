package com.lti.agro.services;

import java.util.List;

import com.lti.agro.dto.SalesViewDto;
import com.lti.agro.entity.Bidder;
import com.lti.agro.entity.Sales;

public interface BidderServices {
	public boolean addOrUpdateABidder(Bidder bidder);
	public List<Sales> purchaseHistory(int bId);
	public List<SalesViewDto> viewWonAuction(int bId);
	public boolean makePayment(int sId);
	public boolean placeBid(int sId, double biddingAmount,int bId);
}
