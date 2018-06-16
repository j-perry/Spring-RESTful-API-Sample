package com.sample.spring.api;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.spring.models.Purchase;
import com.sample.spring.services.PurchaseService;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

	@Autowired
	private PurchaseService purchaseService;
	
	@RequestMapping(value = "/{customerId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Set<Purchase> getPurchases(@PathVariable("customerId") long customerId) throws Exception {
		return purchaseService.getPurchasesByCustomerId(customerId);
	}
	
	@RequestMapping(method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Purchase> getPurchases(@RequestParam("purchaseId") long purchaseId, 
			@RequestParam("purchaseDate") String purchaseDate) throws Exception {
		return purchaseService.getPurchasesByCustomerIdAndDate(purchaseId, purchaseDate);
	}
	
	@RequestMapping(value = "/create",
		method = RequestMethod.POST,
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Purchase> create(@RequestBody Purchase purchase) {
		try {
			purchaseService.addPurchase(purchase);
			return new ResponseEntity<Purchase>(HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@RequestMapping(value = "/update",
		method = RequestMethod.PUT,
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Integer> updatePurchase(@RequestBody Purchase purchase) {
		try {
			purchaseService.updatePurchase(purchase);
			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@RequestMapping(value = "/delete",
		method = RequestMethod.GET)
	public ResponseEntity<Purchase> deletePurchase(@RequestParam("purchaseId") long purchaseId, 
			@RequestParam("purchaseDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date purchaseDate) {
		try {
			purchaseService.deletePurchase(purchaseId, purchaseDate);
			return new ResponseEntity<Purchase>(HttpStatus.ACCEPTED);
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
}