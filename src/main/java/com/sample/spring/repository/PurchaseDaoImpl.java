package com.sample.spring.repository;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sample.spring.models.Customer;
import com.sample.spring.models.Purchase;

@Repository
@Transactional
public class PurchaseDaoImpl implements PurchaseDao {

//	private static final Logger logger = LoggerFactory.getLogger(PurchaseDaoImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Purchase addPurchase(Purchase purchase) {
		entityManager.persist(purchase);
		return purchase;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Set<Purchase> getPurchasesByCustomerId(long customerId) {
		 List<Purchase> purchasesOrig = entityManager.createQuery("SELECT purchase FROM Purchase purchase WHERE purchase.customerId = :customerId")
				.setParameter("customerId", (Long) customerId)
				.getResultList();
		 Set<Purchase> purchases = new HashSet<Purchase>(purchasesOrig);
		 return purchases;
	}

	public int updatePurchase(Purchase purchase) {
		String query = "update Purchase p " +
			"set p.price = :price, " +
			"p.item = :item " +
			"where p.customerId = :customerId and " + 
				"p.purchaseId = :purchaseId";
		return entityManager.createQuery(query)
				.setParameter("price", purchase.getPrice())
				.setParameter("item", purchase.getItem())
				.setParameter("customerId", purchase.getCustomerId())
				.setParameter("purchaseId", purchase.getPurchaseId())
				.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Purchase> listPurchases() {
		return entityManager.createQuery("select purchase from Purchase purchase").getResultList();
	}

	@Transactional(readOnly = true)
	public Purchase getPurchaseById(long purchaseId) {
		return (Purchase) entityManager.createQuery("select purchase from Purchase purchase where purchase.purchaseId = :purchaseId")
				.setParameter("purchaseId", purchaseId)
				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Purchase> getPurchasesByCustomerIdAndDate(long purchaseId, Date date) {
		return (List<Purchase>) entityManager.createQuery("select purchase from Purchase purchase where purchase.purchaseId = :purchaseId AND purchase.date = :date")
				.setParameter("purchaseId", purchaseId)
				.setParameter("date", date)
				.getResultList();
	}

	public void removePurchase(long purchaseId, Date date) {
		Query query = entityManager.createNativeQuery("delete purchase from Purchase purchase where purchase.purchaseId = :purchaseId and purchase.date = :purchaseDate");
		query.setParameter("purchaseId", purchaseId);
		query.setParameter("purchaseDate", date);
		query.executeUpdate();
	}

}