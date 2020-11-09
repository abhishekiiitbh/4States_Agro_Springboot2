package com.lti.agro.repository;

import java.util.List;

import com.lti.agro.entity.Bidder;

public interface BidderDao {
	public Bidder addOrUpfateBidder(Bidder bidder);
	public Bidder findBidderByAadharCardNo(String AadharCard);
	public Bidder findBidderById(int bId);
	public List<Bidder> viewAllBidders();
	public Bidder findBidderByEmaiId(String email);
}
