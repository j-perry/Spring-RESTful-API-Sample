package com.sample.spring.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.spring.models.Purchase;
import com.sample.spring.repository.PurchaseDao;

@Service
public class PurchaseService {
	
	@Autowired
	private PurchaseDao purchaseRepo;

	public Set<Purchase> getPurchasesByCustomerId(long customerId) {
		return purchaseRepo.getPurchasesByCustomerId(customerId);
	}
	
	public List<Purchase> getPurchasesByCustomerIdAndDate(long customerId, String purchaseDate) throws Exception {
		Date d = new SimpleDateFormat("yyyy-MM-dd").parse(purchaseDate);
		return purchaseRepo.getPurchasesByCustomerIdAndDate(customerId, d);
	}
	
	public Purchase addPurchase(Purchase purchase) {
		return purchaseRepo.addPurchase(purchase);
	}
	
	public int updatePurchase(Purchase purchase) {
		return purchaseRepo.updatePurchase(purchase);
	}
	
	public void deletePurchase(long customerId, Date date) {
		purchaseRepo.removePurchase(customerId, date);
	}
	
}
