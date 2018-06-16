package com.sample.spring.services;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sample.spring.models.Customer;
import com.sample.spring.repository.CustomerDao;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerDao customerRepository;

	public Customer getByCustomerId(long customerId) {
		return customerRepository.getCustomer(customerId);
	}

	@Transactional(propagation = Propagation.REQUIRED,
		readOnly = false)
	public Customer addCustomer(Customer customer) {
		return customerRepository.addCustomer(customer);
	}

	public Set<Customer> getCustomerPurchasesById(long customerId) {
		return customerRepository.getPurchasesByCustomerId(customerId);
	}
	
	public void deleteCustomer(long customerId) {
		customerRepository.deleteCustomer(customerId);
	}
	
	public ArrayList<Customer> getCustomers() {
		return customerRepository.getCustomers();
	}
	
	public Customer updateCustomer(Customer customer) {
		return customerRepository.updateCustomer(customer);
	}

}
