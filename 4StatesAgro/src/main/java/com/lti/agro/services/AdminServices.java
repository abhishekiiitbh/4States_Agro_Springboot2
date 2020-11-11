package com.lti.agro.services;

import java.util.List;

import com.lti.agro.dto.AdminViewInsuranceApplicationDto;
import com.lti.agro.dto.InsuranceClaimDto;
import com.lti.agro.dto.RequestDto;
import com.lti.agro.entity.Bidder;
import com.lti.agro.entity.Farmer;
import com.lti.agro.entity.Sales;

public interface AdminServices {
	public Farmer approveFarmerRegistration(int fId);
	public Sales approveSellRequest(int salesId,double basePrice);
	public Sales finalizeBid(int sId);
	public Bidder approveBidderRegistration(int bId);
	public List<AdminViewInsuranceApplicationDto> viewRequest();
	public boolean AdminApprovalForInsuranceApplication(int policyNo);
	public boolean AdminApprovalForInsuranceClaim(int rId,double amountClaim);
	public List<RequestDto> viewFarmerRequests();
	public List<RequestDto> viewBidderRequests();
	public List<InsuranceClaimDto> viewClaimRequests();
}
