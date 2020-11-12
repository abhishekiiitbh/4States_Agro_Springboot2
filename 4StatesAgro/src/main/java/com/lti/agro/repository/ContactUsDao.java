package com.lti.agro.repository;

import java.util.List;

import com.lti.agro.entity.ContactUsRequest;

public interface ContactUsDao {
	public void addAMessage(ContactUsRequest cntusreq);
	public List<ContactUsRequest> viewAllMessages();
	public ContactUsRequest findById(int cId);
}
