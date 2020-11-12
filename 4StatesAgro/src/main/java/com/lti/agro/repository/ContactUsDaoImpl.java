
package com.lti.agro.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lti.agro.entity.ContactUsRequest;



@Repository
public class ContactUsDaoImpl implements ContactUsDao{

	@PersistenceContext
	EntityManager em;
	
	
	@Transactional
	public void addAMessage(ContactUsRequest cntusreq) {
		
		em.merge(cntusreq);
		System.out.println("Message added.");

	}
	
	public List<ContactUsRequest> viewAllMessages(){
		Query query=em.createQuery("select c from ContactUsRequest c",ContactUsRequest.class);
		return query.getResultList();
	}
	
	public ContactUsRequest findById(int cId)
	{
		return em.find(ContactUsRequest.class,cId);
	}
	
	
	
}

