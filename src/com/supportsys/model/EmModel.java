package com.supportsys.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmModel {

	/**
	 * Get EntityManager
	 * @return
	 */
	public EntityManager getEm()
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("support");
		EntityManager em = emf.createEntityManager();
		return em;
	}

	public EntityManager closeEm(EntityManager em)
	{
		em.close();
		return em;
	}
}
