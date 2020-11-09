package com.lti.agro.repository;

import com.lti.agro.entity.InsuranceClaim;

public interface InsuranceClaimDao {
	public InsuranceClaim placeAClaimRequest(InsuranceClaim claim);
	public InsuranceClaim checkClaimExists(int policyNo);
	public InsuranceClaim showPreviousClaimByFid(int fid);
	public InsuranceClaim findByRId(int rId);
}
