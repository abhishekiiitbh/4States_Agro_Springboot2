package com.lti.agro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.agro.entity.InsuranceApplications;
import com.lti.agro.entity.InsuranceClaim;
import com.lti.agro.repository.InsuranceClaimDao;

@Service
public class InsuranceClaimServicesImpl implements InsuranceClaimServices {

	@Autowired
	InsuranceClaimDao insuranceClaimDao;
	@Autowired
	InsuranceApplicationService insuranceServiceImpl;
	@Autowired
	EmailService emailService;

	public int addOrUpdateInsuranceClaim(InsuranceClaim insuranceClaim, int policyNo) {

		InsuranceApplications app=insuranceServiceImpl.findInsurnceByPolicyNo(policyNo);
		if ( app!= null&&app.isStatus()==true) {
			
			System.out.println("True");
			try {
				insuranceClaimDao.checkClaimExists(policyNo);
				System.out.println("false");
				String text = "Your Claim Request for Policy NO:"+policyNo +"Failed!"; 
				String subject = "Registration Rejected!";
				emailService.sendEmailForNewRegistration(app.getEmail(), text, subject);
				
				return 0;
			} catch (Exception e) {
                insuranceClaim.setInsuranceapplication(app);
//              app.setInsuranceclaim(insuranceClaim);
				InsuranceClaim newclaim = insuranceClaimDao.placeAClaimRequest(insuranceClaim);
				String text = "Your Claim Request for Policy NO:"+policyNo +"\n Please Await for the confirmation from our team!"; 
				String subject = "Registration Aproved!";
				emailService.sendEmailForNewRegistration(app.getEmail(), text, subject);
				return newclaim.getrId();
			}
		}
		else {
			return 0;
		}

	}
	public InsuranceClaim showPreviousClaimByFid(int fid) {
		InsuranceClaim ic=insuranceClaimDao.showPreviousClaimByFid(fid);
		
		return ic;
	}
	@Override
	public InsuranceClaim findClaimById(int rId) {
		return insuranceClaimDao.findByRId(rId);
	}
	@Override
	public boolean updateClaim(InsuranceClaim insuranceClaim) {
		InsuranceClaim ic = insuranceClaimDao.placeAClaimRequest(insuranceClaim);
		if(ic!=null)
			return true;
		return false;
	}
	

}
