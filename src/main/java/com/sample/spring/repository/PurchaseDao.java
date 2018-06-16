package com.sample.spring.repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.sample.spring.models.Purchase;

public interface PurchaseDao {
	
	public Purchase addPurchase(Purchase p);
	public int updatePurchase(Purchase p);
	public List<Purchase> listPurchases();
	public Purchase getPurchaseById(long purchaseId);
	public List<Purchase> getPurchasesByCustomerIdAndDate(long purchaseId, Date date);
	public Set<Purchase> getPurchasesByCustomerId(long purchaseId);
	public void removePurchase(long purchaseId, Date date);

}