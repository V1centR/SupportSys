package com.supportsys.repo;

import java.util.List;

import javax.persistence.EntityManager;

import com.supportsys.entity.Client;
import com.supportsys.entity.Uf;
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

	/*
	 * List all staets
	 * @return List<Client>
	 */
	public List<Uf> getAllStates()
	{
		EntityManager em = super.getEm();

		List<Uf> fullList = em.createNamedQuery("Uf.findAll").getResultList();
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

	/**
	 * List clients by keyword
	 * @return List<Client>
	 */
	public Client getClientInfo(Integer idClient)
	{
		EntityManager em = super.getEm();

		String query = "SELECT c FROM Client c WHERE c.id = :idClient";

		Client itemData = em.createQuery(query, Client.class).
				setParameter("idClient", idClient).
				getSingleResult();
		em.close();

		return itemData;
	}

}
