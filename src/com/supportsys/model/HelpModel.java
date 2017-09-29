package com.supportsys.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.json.JSONException;
import org.json.JSONObject;

import com.supportsys.entity.Client;
import com.supportsys.entity.Help;

public class HelpModel {
	
	public boolean addHelp(Help help) throws JSONException
	{
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("support");
		EntityManager em = emf.createEntityManager();
		
		Object helpLabel = help.getHelpLabel().toString();
		
		System.out.println("Saida de dentro da Model: " + helpLabel);
		
		
		Client cliente = em.find(Client.class, 1);
		cliente.getName();
		cliente.getEmail();
		
		//String firstName = help.getString("helpLabel");
	
		
		return true;
		
	}

}
