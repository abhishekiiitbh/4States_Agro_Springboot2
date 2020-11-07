package com.lti.agro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.agro.entity.InsuranceClaim;
import com.lti.agro.repository.InsuranceClaimDaoImpl;

@Service
public class InsuranceClaimServices {

	@Autowired
	InsuranceClaimDaoImpl insuranceClaimDao;
	@Autowired
	InsuranceApplicationServiceImpl insuranceServiceImpl;

	public boolean addOrUpdateInsuranceClaim(InsuranceClaim insuranceClaim, int policyNo) {

		try {
			insuranceServiceImpl.findInsurnceByPolicyNo(policyNo);
			
			
		} catch (Exception e1) {

			return false;

		}
		try {
			insuranceClaimDao.checkClaimExists(policyNo);
			return false;
		} catch (Exception e) {

			InsuranceClaim newclaim = insuranceClaimDao.placeAClaimRequest(insuranceClaim);
			return true;
		}

	}
	public InsuranceClaim showPreviousClaimByFid(int fid) {
		InsuranceClaim ic=insuranceClaimDao.showPreviousClaimByFid(fid);
		
		return ic;
	}

}
