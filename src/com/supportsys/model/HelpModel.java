package com.supportsys.model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.catalina.Manager;
import org.apache.commons.collections.Factory;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManager;
import org.hibernate.jpa.HibernateQuery;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.ResponseStatus;

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
				em.close();
			}

		} catch (Exception e) {
			// TODO: handle exception
			execOk = false;
		}
		
		return execOk;
		
	}
	
	public List<Help> list()
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("support");
		EntityManager em = emf.createEntityManager();
		
		
		//Department deptCall = em.find(Department.class, dept);
		
		List<Help> helpList = em.createNamedQuery("Help.findAll").getResultList();
		
//		for(Object[] dataItem: helpList) {
//			
//			System.out.println(dataItem[1]);
//		}

		return helpList;
	}

}
