package com.sample.spring.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.sample.spring.models.Customer;

public interface CustomerDao {
	
	public Customer addCustomer(Customer customer);
	public Customer updateCustomer(Customer customer);
	public void deleteCustomer(long customerId);
	public Customer getCustomer(long customerId);
	public Set<Customer> getPurchasesByCustomerId(long customerId);
	public ArrayList<Customer> getCustomers();

}
