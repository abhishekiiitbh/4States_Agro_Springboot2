package com.lti.agro.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.lti.agro.entity.Bidder;
import com.lti.agro.entity.Farmer;

@Repository
public class SignInDaoImpl implements SignInDao {
	
	@PersistenceContext
	EntityManager em;
	
	public Farmer signInByFarmer(String email,String password) {
		String jpql="select f from Farmer f where f.email=:eml and f.password=:pwd and f.approval=:approval";
		TypedQuery<Farmer> query=em.createQuery(jpql,Farmer.class);
		query.setParameter("eml", email);
		query.setParameter("pwd", password);
		query.setParameter("approval", true);
		
		return query.getSingleResult();
	}
	
	public Bidder signInByBidder(String email,String password)
	{
		String jpql="select b from Bidder b where b.email=:eml and b.password=:pwd and b.approval=:approval";
		// here we will check approval if it is "Yes" then check login credentails
		
		TypedQuery<Bidder> query=em.createQuery(jpql,Bidder.class);
		query.setParameter("eml", email);
		query.setParameter("pwd", password);
		query.setParameter("approval", true);
		return query.getSingleResult();
	}
}
