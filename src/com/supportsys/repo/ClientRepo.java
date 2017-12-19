package com.supportsys.repo;

import java.util.List;

import javax.persistence.EntityManager;

import com.supportsys.entity.Client;
import com.supportsys.model.EmModel;

public class ClientRepo extends EmModel {

	/*
	 * List all clients
	 * @return List<Client>
	 */
	public List<Client> getAllClients()
	{
		EntityManager em = super.getEm();

		List<Client> fullList = em.createNamedQuery("Client.findAll").getResultList();
		em.close();

		return fullList;
	}


	/**
	 * List clients by keyword
	 * @return List<Client>
	 */
	public List<Client> getClientsByKeyWord(String keyWord)
	{
		EntityManager em = super.getEm();

		String query = "SELECT c FROM Client c WHERE c.name LIKE :keyWord";

		List<Client> itemData = em.createQuery(query, Client.class).
				setParameter("keyWord", "%"+keyWord+"%").
				getResultList();
		em.close();

		return itemData;
	}

}
