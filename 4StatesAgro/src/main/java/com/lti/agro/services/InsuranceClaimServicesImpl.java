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

	public boolean addOrUpdateInsuranceClaim(InsuranceClaim insuranceClaim, int policyNo) {

		InsuranceApplications app=insuranceServiceImpl.findInsurnceByPolicyNo(policyNo);
		if ( app!= null) {
			
			System.out.println("True");
			try {
				insuranceClaimDao.checkClaimExists(policyNo);
				return false;
			} catch (Exception e) {
                insuranceClaim.setInsuranceapplication(app);
//              app.setInsuranceclaim(insuranceClaim);
				insuranceClaimDao.placeAClaimRequest(insuranceClaim);
				String text = "Your Claim Request for Policy NO:"+policyNo +"\n Please Await for the confirmation from our team!"; 
				String subject = "Registration Aproved!";
				emailService.sendEmailForNewRegistration(app.getEmail(), text, subject);
				return true;
			}
		}else {
			System.out.println("false");
			String text = "Your Claim Request for Policy NO:"+policyNo +"Failed!"; 
			String subject = "Registration Rejected!";
			emailService.sendEmailForNewRegistration(app.getEmail(), text, subject);
			return false;
		}

	}
	public InsuranceClaim showPreviousClaimByFid(int fid) {
		InsuranceClaim ic=insuranceClaimDao.showPreviousClaimByFid(fid);
		
		return ic;
	}
	

}
