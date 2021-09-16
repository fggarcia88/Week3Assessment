package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import java.util.List;

import model.ArcadeInventory;

/**
 * @author Fernando Garcia - fggarcia
 * CIS175 - Fall 2021
 * Sep 16, 2021
 */
public class ArcadeInventoryHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ArcadeInventory");

	public List<ArcadeInventory> showAllArcades() {
		EntityManager em = emfactory.createEntityManager();
		List<ArcadeInventory> allArcades = em.createQuery("SELECT i from ArcadeInventory i").getResultList();
		return allArcades;
	}
	
	public void insertArcade(ArcadeInventory toInsert) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(toInsert);
		em.getTransaction().commit();
		em.close();		
	}
	
	public void deleteArcade(ArcadeInventory toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ArcadeInventory> typedQuery = em.createQuery("select ai from "
				+ "ArcadeInventory ai where ai.title = :selectedTitle and ai.costPerPlay = "
				+ ":selectedCostPerPlay and ai.purchasePrice = :selectedPurchasePrice", ArcadeInventory.class);
		typedQuery.setParameter("selectedTitle", toDelete.getTitle());
		typedQuery.setParameter("selectedCostPerPlay", toDelete.getCostPerPlay());
		typedQuery.setParameter("selectedPurchasePrice", toDelete.getPurchasePrice());
		
		typedQuery.setMaxResults(1);
		
		ArcadeInventory result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
		
	}
	
	public void updateItem(ArcadeInventory toUpdate) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toUpdate);
		em.getTransaction().commit();
		em.close();		
	}

	public ArcadeInventory lookupArcadeById(int toLookup) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		ArcadeInventory found = em.find(ArcadeInventory.class, toLookup);
		em.close();
		return found;
	}

	public void cleanUp() {
		emfactory.close();
	}


}
