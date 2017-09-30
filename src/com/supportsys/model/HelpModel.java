package com.supportsys.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.json.JSONException;
import org.json.JSONObject;

import com.supportsys.entity.Client;
import com.supportsys.entity.Help;

public class HelpModel {

	public boolean addHelp(JSONObject jsonItems) throws JSONException
	{
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("support");
		EntityManager em = emf.createEntityManager();
		
		//Strings ###
//		Object formMode = jsonItems.get("formMode").toString();
//		Object helpLabel = jsonItems.get("helpLabel").toString();
//		Object description = jsonItems.get("description").toString();
		
		//integers ###
		Object client = Integer.parseInt(jsonItems.get("client").toString());
		
		Object idAtividade = jsonItems.get("idAtividade").toString();		
		Object category = jsonItems.get("category").toString();
		Object dept = jsonItems.get("dept").toString();
		
		
		
		
		System.out.println("Integer na Model:: " + client);
		
		
		// int i = Integer.valueOf((String) object);
		
		
		
		Client cliente = em.find(Client.class, 1);
		cliente.getName();
		cliente.getEmail();
		
		//String firstName = help.getString("helpLabel");
	
		
		return true;
		
	}

}
