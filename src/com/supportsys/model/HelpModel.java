package com.supportsys.model;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.json.JSONException;
import org.json.JSONObject;

import com.supportsys.entity.Client;
import com.supportsys.entity.Department;
import com.supportsys.entity.Help;
import com.supportsys.entity.Status;
import com.supportsys.entity.TypeHelp;
import com.supportsys.entity.User;

public class HelpModel {

	public boolean createHelp(JSONObject jsonItems, Integer user) throws JSONException
	{
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("support");
		EntityManager em = emf.createEntityManager();
		
		
		System.out.println(jsonItems.toString());
		
		//Strings ###
		Object formMode = jsonItems.get("formMode").toString();
		Object helpLabel = jsonItems.get("helpLabel").toString();
		Object helpTxt = jsonItems.get("description").toString();
		
		//integers ###
		//Object client = Integer.parseInt(jsonItems.get("client").toString());
		//Object idAtividade = Integer.parseInt(jsonItems.get("idAtividade").toString());
		Object category = Integer.parseInt(jsonItems.get("category").toString());
		Object dept = Integer.parseInt(jsonItems.get("dept").toString());
		
		String tags = "Testes;Hibernate;JPA;MariaDb;Setembro2017";
		
		//TimeStamp
		long now = Calendar.getInstance().getTimeInMillis();
		Timestamp tsNow = new Timestamp(now);
		
		//System.out.println("Integer na Model:: " + client);
		
		
		//Client cliente = em.find(Client.class, 1);
		//cliente.getName();
		User userCall = em.find(User.class, user);
		
		//Default value is 1 in database
		// is required because entity config JPA
		Status status = em.find(Status.class, 1);
		
		Department deptCall = em.find(Department.class, dept);
		
		TypeHelp typeHelp = em.find(TypeHelp.class, category);
				
		Boolean execOk = false;
		try {
			
			Help help = new Help();
			
			help.setHelpLabel((String)helpLabel);
			help.setHelpTxt((String)helpTxt);
			help.setUser(userCall);			
			help.setStatusBean(status);
			help.setDepartment(deptCall);
			help.setDateHelp(tsNow);
			
			help.setTypeHelpBean(typeHelp);
			help.setTags(tags);
			
			em.getTransaction().begin();
			em.persist(help);
			em.getTransaction().commit();

			Integer idTransAction = help.getId();
			
			if(idTransAction == (int)idTransAction) {
				execOk = true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			execOk = false;
		}
		
		return execOk;
		
	}

}
