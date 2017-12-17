package com.supportsys.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.supportsys.entity.Client;

public class ClientRepo {

	/**
	 * List al clients
	 * @return List<Client>
	 */
	public List<Client> getClientsByKeyWord(String keyWord)
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("support");
		EntityManager em = emf.createEntityManager();

		String query = "SELECT c FROM Client c WHERE c.name LIKE :keyWord";

		List<Client> itemData = em.createQuery(query, Client.class).
		setParameter("keyWord", keyWord+"%").
		getResultList();
		emf.close();

		return itemData;
	}

}
