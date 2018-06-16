package com.sample.spring.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sample.spring.models.Customer;

@Repository
@Transactional(readOnly = true)
public class CustomerDaoImpl implements CustomerDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Customer addCustomer(Customer customer) {
		entityManager.persist(customer);
		return customer;
	}
	
	public Customer updateCustomer(Customer customer) {
		Customer newCustomer = customer;
		newCustomer.setDate(new Date());
		entityManager.merge(newCustomer);
		return customer;
	}
	
	public void deleteCustomer(long customerId) {
		Query query = entityManager.createNativeQuery("delete customer from Customer customer where customer.customerId = :customerId");
		query.setParameter("customerId", customerId);
		query.executeUpdate();
	}
	
	@Transactional(readOnly = true)
	public Customer getCustomer(long customerId) {		
		try {
			Query query = entityManager.createQuery("select customer from Customer customer where customer.customerId = :customerId");
			query.setParameter("customerId", customerId);
			
			return (Customer) query.getResultList().get(0);
		} catch(Exception ex) {
			// this will throw an unchecked exception
			ex.printStackTrace();
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Set<Customer> getPurchasesByCustomerId(long customerId) {
		String query = "SELECT c FROM Customer c JOIN c.purchases p WHERE p.customerId = :customerId";
		Set<Customer> purchases = null;
		
		try {
			Query q = entityManager.createQuery(query);
			q.setParameter("customerId", customerId);
			purchases = new HashSet<Customer>(q.getResultList());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return purchases;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Customer> getCustomers() {
		return (ArrayList<Customer>) entityManager.createQuery("select customer from Customer customer").getResultList();
	}
	
}