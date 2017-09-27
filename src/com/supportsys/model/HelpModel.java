package com.supportsys.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.json.JSONException;
import org.json.JSONObject;

import com.supportsys.entity.Client;

public class HelpModel {
	
	public boolean addHelp(JSONObject dataItem) throws JSONException
	{
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("support");
		EntityManager em = emf.createEntityManager();
		
		Client cliente = em.find(Client.class, 1);
		cliente.getName();
		cliente.getEmail();
		
		String firstName = dataItem.getString("helpLabel");
	
		
		return true;
		
	}

}
