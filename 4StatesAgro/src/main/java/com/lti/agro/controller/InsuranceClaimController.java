package com.lti.agro.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.agro.dto.ClaimDocumentDto;
import com.lti.agro.dto.InsuranceClaimDto;
import com.lti.agro.dto.SalesDocumentDto;
import com.lti.agro.dto.Status;
import com.lti.agro.dto.Status.StatusType;
import com.lti.agro.entity.InsuranceClaim;
import com.lti.agro.entity.Sales;
import com.lti.agro.services.InsuranceClaimServices;

@RestController
@CrossOrigin
public class InsuranceClaimController {
	
	@Autowired
	InsuranceClaimServices insuranceClaimServices;
	
	
	@PostMapping(path="/registerClaim")
	
	public Status ClaimApplication(@RequestBody InsuranceClaimDto insuranceClaimDto) {
		
		InsuranceClaim claim=new InsuranceClaim();
		claim.setCauseOfClaim(insuranceClaimDto.getCauseOfClaim());
		claim.setDateOfClaim(insuranceClaimDto.getDateOfClaim());
		claim.setDateOfLoss(insuranceClaimDto.getDateOfLoss());
		claim.setDateOfApproval(LocalDate.of(1, 1, 1));
		claim.setDateOfClaim(LocalDate.now());
		claim.setAmountClaimed(0);
		claim.setTransactionId(null);
		int policyNo=insuranceClaimDto.getPolicyNo();
		int result=insuranceClaimServices.addOrUpdateInsuranceClaim(claim, policyNo);
		
		
		Status status=new Status();
		if(result>0) {
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Claim succesfully submitted");
			status.setId(result);
		}else {
			status.setStatus(StatusType.FAILURE);
			status.setMessage("You have already placed a claim or policy number does not exist");
			status.setId(0);
		}
		return status;
		
	}
	
	@PostMapping("/claimDocumentUploads")
	public Status uploadDocuments(ClaimDocumentDto claim) {
		Status status = new Status();
		InsuranceClaim claimreq = insuranceClaimServices.findClaimById(Integer.parseInt(claim.getrId()));
		 String salesLocation ="D:/uploads/Claim/";
			String claimName1 = claim.getCropImage1().getOriginalFilename();
			String claimFile = salesLocation +claimName1;
			try {
				FileCopyUtils.copy(claim.getCropImage1().getInputStream(), new FileOutputStream(claimFile));
			}
			catch (IOException e) {
	            e.printStackTrace();
	            status.setStatus(StatusType.FAILURE);
	            status.setMessage(e.getMessage());
	            return status;
			}
			String claimName2 = claim.getCropImage2().getOriginalFilename();
			claimFile = salesLocation + claimName2;
			try {
				FileCopyUtils.copy(claim.getCropImage2().getInputStream(), new FileOutputStream(claimFile));
			}
			catch (IOException e) {
	            e.printStackTrace();
	            status.setStatus(StatusType.FAILURE);
	            status.setMessage(e.getMessage());
	            return status;
			}
			
		System.out.println(claimName1);
		System.out.println(claimName2);
		claimreq.setCropImage1(claimName1);
		claimreq.setCropImage2(claimName2);
		boolean result = insuranceClaimServices.updateClaim(claimreq);
		if(result) {
		status.setStatus(StatusType.SUCCESS);
        status.setMessage("Uploaded Successfully!");
		}
		else
		{
			status.setStatus(StatusType.FAILURE);
	        status.setMessage("Uploaded Failed!");
		}
        return status;
		
	}
	
	@GetMapping(path = "/retrieveClaimDocuments")
	public InsuranceClaim viewDocuments(@RequestParam("rId") int id,HttpServletRequest request)
	{
		InsuranceClaim claim=insuranceClaimServices.findClaimById(id);
		String projPath = request.getServletContext().getRealPath("/");
		String tempDownloadPath = projPath +"/downloads/";
		File f = new File(tempDownloadPath);
		if(!f.exists())
			f.mkdir();
		
		String salesDocumentLocation="D:/uploads/Claim/";
		
		String aadhartargetFile = tempDownloadPath + claim.getCropImage1();
		System.out.println(aadhartargetFile);
		String panCardtargetFile = tempDownloadPath + claim.getCropImage2();
		System.out.println(panCardtargetFile);
		String aadharsourceFile =  salesDocumentLocation + claim.getCropImage1();
		System.out.println(aadharsourceFile);
		String panCardsourceFile = salesDocumentLocation + claim.getCropImage1();
		System.out.println(panCardsourceFile);
		try {
			FileCopyUtils.copy(new File(aadharsourceFile), new File(aadhartargetFile));
			FileCopyUtils.copy(new File(panCardsourceFile), new File(panCardtargetFile));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		claim.setInsuranceapplication(null);
		
		return claim;
	}
	
	@GetMapping(path = "/show-claim")

	public InsuranceClaim showPreviousclaim(@RequestParam("fId") int fId) {
		InsuranceClaim ic = insuranceClaimServices.showPreviousClaimByFid(fId);
		return ic;
	}

}
