package com.sample.spring.api;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.spring.models.Customer;
import com.sample.spring.services.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer getRecordsByCustomerId(@RequestParam("customerId") long customerId) throws Exception {
		return customerService.getByCustomerId(customerId);
	}
	
	@RequestMapping(value = "/all",
		method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<Customer> getCustomers() throws Exception {
		return customerService.getCustomers();
	}
	
	@RequestMapping(value = "/purchases",
		method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public Set<Customer> getPurchasesByCustomerId(@RequestParam("customerId") long customerId) {
		return customerService.getCustomerPurchasesById(customerId);
	}
	
	@RequestMapping(value = "/create",
		method = RequestMethod.POST,
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer addCustomer(@RequestBody Customer customer) throws Exception {
		return customerService.addCustomer(customer);
	}
	
	@RequestMapping(value = "/delete",
		method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> deleteCustomer(@RequestParam("customerId") long customerId) throws Exception {
		try {
			this.customerService.deleteCustomer(customerId);
			return new ResponseEntity<Customer>(HttpStatus.ACCEPTED);
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@RequestMapping(value = "/update",
		method = RequestMethod.PUT,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) throws Exception {
		try {
			this.customerService.updateCustomer(customer);
			return new ResponseEntity<Customer>(HttpStatus.ACCEPTED);
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

}