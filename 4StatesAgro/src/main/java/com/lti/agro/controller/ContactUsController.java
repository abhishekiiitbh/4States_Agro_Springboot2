package com.lti.agro.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.agro.services.EmailService;
import com.lti.agro.dto.Status;
import com.lti.agro.dto.Status.StatusType;
import com.lti.agro.entity.ContactUsRequest;
import com.lti.agro.repository.ContactUsDao;

@CrossOrigin
@RestController
public class ContactUsController {
	
	@Autowired
	private EmailService emailservice;
	
	@Autowired
	ContactUsDao contactusdaoimpl;
	
	@PostMapping(path="/ContactUsRequest")
	public Status ContactUsRequest(@RequestBody ContactUsRequest contactusrequest){
		
		contactusrequest.setStatus(false);
		
		contactusdaoimpl.addAMessage(contactusrequest);
		String subject="Message sent successfully!";
		String text="We need your patience for now. Soon we will get back to you to resolve your query.Thanks for contacting.";
		
		emailservice.sendEmailForNewRegistration(contactusrequest.getEmail(), text, subject);
		
		Status status=new Status();
		status.setStatus(StatusType.SUCCESS);
		status.setMessage("Request sent sucessfully");
		
		return status;
	}
	
	@GetMapping(path = "/viewAllMessages")
	public List<ContactUsRequest> viewAllMessages() {
		List<ContactUsRequest> messages=new ArrayList<>();
		List<ContactUsRequest> msgs = contactusdaoimpl.viewAllMessages();
		for(ContactUsRequest msg:msgs)
		{
			if(msg.isStatus()==false)
			{
				ContactUsRequest message = new ContactUsRequest();
				message.setcId(msg.getcId());
				message.setEmail(msg.getEmail());
				message.setName(msg.getName());
				message.setMessage(msg.getMessage());
				messages.add(message);
			}
		}
		return messages;
	}
	
	@PostMapping(path = "/replyMessage")
	public Status replyMessage(@RequestParam("id")int cId,@RequestParam("email")String email,@RequestParam("message")String message)
	{	Status status=new Status();
		ContactUsRequest contact=contactusdaoimpl.findById(cId);
		if(contact!=null)
		{
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Replied");
		}
		else
		{
			status.setStatus(StatusType.FAILURE);
			status.setMessage("Failed");
		}
		contact.setStatus(true);
		contactusdaoimpl.addAMessage(contact);;
		
		String subject="4StatesAgro Customer Support";
		String text=message;
		emailservice.sendEmailForNewRegistration(email, text, subject);
		
		return status;
	}
}