package com.lti.agro.services;

public interface ForgotPasswordServices {
	public boolean forgotPasswordEmailCheck(String email,String userType);
	public boolean resetPassword(String otp,String newPassword,String userType);
	
}
