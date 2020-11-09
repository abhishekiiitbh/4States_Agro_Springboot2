package com.lti.agro.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lti.agro.entity.ContactUsRequest;
import com.lti.agro.entity.Sales;



@Repository
public class SalesDaoImpl implements SalesDao {
	
	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public Sales addOrUpdateSales(Sales sale) {
		return em.merge(sale);
	}
	
	public Sales findSalesById(int sId) {
		return  em.find(Sales.class, sId);
		
	}
	
	
	public List<Sales> viewAllSales() {
		
		
		Query query=em.createQuery("Select s from Sales s",Sales.class);
		return query.getResultList();
	}
	
	public void findSalesByFarmerId(int fId) {
		String jpql="Select s from Sales s where s.fid=:farmerId";
		TypedQuery<Sales> query = em.createQuery(jpql, Sales.class);
		query.setParameter("farmerId", fId);
		Sales sale=query.getSingleResult();
		System.out.println(sale);
	}
	
	public List<Sales> findSalesByBidder(int bId) {
		
		String jpql="Select s from Sales s where s.bidder.bId=:bidderId";
		TypedQuery<Sales> query = em.createQuery(jpql, Sales.class);
		query.setParameter("bidderId", bId);
		return query.getResultList();
	}
	
	
}
