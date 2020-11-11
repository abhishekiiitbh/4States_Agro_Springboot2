package com.lti.agro.services;

import com.lti.agro.dto.LoginStatus;

public interface SignInService {
	public LoginStatus signIn(String email,String password,String userType);
}
