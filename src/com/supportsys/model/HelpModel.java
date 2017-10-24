package com.supportsys.model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.supportsys.entity.Department;
import com.supportsys.entity.Help;
import com.supportsys.entity.Status;
import com.supportsys.entity.SupportUser;
import com.supportsys.entity.TypeHelp;
import com.supportsys.entity.User;

public class HelpModel {

	
	public boolean updateItem(JSONObject jsonItems) throws JSONException
	{
		EntityManager em = getEm();
		
		Object itemHash = jsonItems.get("hashItem").toString();
		Integer itemId = Integer.parseInt(jsonItems.get("idItem").toString());
		
		//idUserSupport integer
		Object supportUserSet = jsonItems.get("supportUserSet").toString();
		//integer
		Object selectedStatus = jsonItems.get("status").toString();
		String textSolution = jsonItems.get("txtSolution").toString();
		
		Status selectedStatusId = em.find(Status.class, selectedStatus);
		SupportUser supportUserId = em.find(SupportUser.class, supportUserSet);
		
		
		Help helpUpdate = new Help();
		helpUpdate.setId(itemId);
		helpUpdate.setSupportUser(supportUserId);
		helpUpdate.setStatusBean(selectedStatusId);
		helpUpdate.setSolutionTxt(textSolution);
		
		em.getTransaction().begin();
		em.merge(helpUpdate);
		em.getTransaction().commit();
		
		return false;
	}
	
	/**
	 * Creates help item
	 * @param jsonItems
	 * @param user
	 * @return
	 * @throws JSONException
	 */
	public boolean createHelp(JSONObject jsonItems, Integer user,String sessId) throws JSONException
	{
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
		
		//Get unique hash to this item sha1		
		String convertNow = Long.toString(now);
		String hashSecure = genHashItem(convertNow+sessId);
		
		//Client cliente = em.find(Client.class, 1);
		//cliente.getName();
		User userCall = getEm().find(User.class, user);
		
		//Default value is 1 in database
		// is required because entity config JPA
		Status status = getEm().find(Status.class, 1);
		Department deptCall = getEm().find(Department.class, dept);
		TypeHelp typeHelp = getEm().find(TypeHelp.class, category);
		Boolean execOk = false;
		
		EntityManager em = getEm();
		
		try {
			
			Help help = new Help();
			
			help.setHelpLabel((String)helpLabel);
			help.setHelpTxt((String)helpTxt);
			help.setHashSecure(hashSecure);
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
	
	/**
	 * Get Help list
	 * @return
	 */
	public List<Help> list()
	{
		List<Help> helpList = getEm().createNamedQuery("Help.findAll").getResultList();
		getEm().close();

		return helpList;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<TypeHelp> getTypes()
	{
		List<TypeHelp> types = getEm().createNamedQuery("TypeHelp.findAll").getResultList();
		getEm().close();
		
		return types;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Status> getStatus()
	{
		List<Status> statusTypes = getEm().createNamedQuery("Status.findAll").getResultList();
		getEm().close();
		
		return statusTypes;
	}
	
	
	public List<SupportUser> getSupportUsers()
	{
		List<SupportUser> supportUsersList = getEm().createNamedQuery("SupportUser.findAll").getResultList();
		getEm().close();
		
		return supportUsersList;
	}
			
	
	/**
	 * Get list of departments from client
	 * @param clientId
	 * @return
	 */
	public List<Department> getDepartment(Integer clientId)
	{
		CriteriaBuilder criteriaSet = getEm().getCriteriaBuilder();
		CriteriaQuery<Department> departmentData;
		Root<Department> department;
		
		departmentData = criteriaSet.createQuery(Department.class);
		department = departmentData.from(Department.class);
		departmentData.select(department).where(
				criteriaSet.equal(department.get("clientBean"), clientId)
				);
		
		List<Department> dataDepartment = getEm().createQuery(departmentData).getResultList();
		getEm().close();
		
		return dataDepartment;
	}
	
	public List<Help> openHelp(Integer idHelp)
	{
		
		Integer status = 1;
		
		CriteriaBuilder criteriaSet = getEm().getCriteriaBuilder();
		CriteriaQuery<Help> helpData;
		Root<Help> help;
		
		helpData = criteriaSet.createQuery(Help.class);
		help = helpData.from(Help.class);
		helpData.select(help).where(
				criteriaSet.equal(help.get("id"), idHelp),
				criteriaSet.equal(help.get("statusBean"), status)
				);
		
		List<Help> dataHelp = getEm().createQuery(helpData).getResultList();
		getEm().close();
		
		return dataHelp;
	}
	
	
	/**
	 * Get EntityManager
	 * @return
	 */
	private EntityManager getEm()
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("support");
		EntityManager em = emf.createEntityManager();
		
		return em;
	}
	
	/**
	 * Generates unique hash to item
	 * @param now
	 * @return
	 */
	public String genHashItem(String now)
	{
		HashFunction hf = Hashing.sha1();
		HashCode hc = hf.newHasher().putString(now).hash();
		
		return hc.toString();		
	}

}
