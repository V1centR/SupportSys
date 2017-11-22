package com.supportsys.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.supportsys.entity.Client;
import com.supportsys.entity.Department;

public class UserModel {


	public List<Client> getClients()
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("support");
		EntityManager em = emf.createEntityManager();

		List<Client> fullClients = em.createNamedQuery("Client.findAll").getResultList();
		emf.close();

		return fullClients;
	}


	public List<Department> getDepartments(Integer clientId)
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("support");
		EntityManager em = emf.createEntityManager();

		Client clientObj = em.find(Client.class, clientId);

		String query = "SELECT d FROM Department d WHERE d.clientBean= :idClient";
		List<Department> itemData = em.createQuery(query, Department.class).
		setParameter("idClient", clientObj).
		getResultList();
		emf.close();

		return itemData;


	}

}
