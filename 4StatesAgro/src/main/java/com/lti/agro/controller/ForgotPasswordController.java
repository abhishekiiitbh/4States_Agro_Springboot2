package com.lti.agro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.agro.dto.Status;
import com.lti.agro.dto.Status.StatusType;
import com.lti.agro.services.ForgotPasswordServices;
@CrossOrigin
@RestController
public class ForgotPasswordController {
	
	@Autowired
	ForgotPasswordServices forgotPasswordService;
	
	@PostMapping(path = "/forgotPassword")
	public Status forgotPassword(@RequestParam("email")String email,@RequestParam("userType") String userType) {
		boolean result=forgotPasswordService.forgotPasswordEmailCheck(email, userType);
		Status status = new Status();
		if(result)
		{
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("OTP HAS BEEN SENT SUCCESSFULLY!");
		}
		else
		{
			status.setStatus(StatusType.FAILURE);
			status.setMessage("EMAIL IS NOT REGISTERED!");
		}
		return status;
	}
	
	@PostMapping(path = "/resetPassword")
	public Status resetPassword(@RequestParam("otp") String otp,@RequestParam("user") String userType,@RequestParam("password") String password) {
		boolean result = forgotPasswordService.resetPassword(otp, password, userType);
		Status status = new Status();
		if(result)
		{
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("PASSWORD CHANGED SUCCESSFULLY!");
		}
		else
		{
			status.setStatus(StatusType.FAILURE);
			status.setMessage("OTP ENTERED WAS WRONG!");
		}
		return status;
	}
}
