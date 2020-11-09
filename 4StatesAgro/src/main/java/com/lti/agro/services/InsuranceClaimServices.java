package com.lti.agro.services;

import com.lti.agro.entity.InsuranceClaim;

public interface InsuranceClaimServices {
	public boolean addOrUpdateInsuranceClaim(InsuranceClaim insuranceClaim, int policyNo);
	public InsuranceClaim showPreviousClaimByFid(int fid);
}
