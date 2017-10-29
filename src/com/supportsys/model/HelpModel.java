package com.supportsys.model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.supportsys.entity.Department;
import com.supportsys.entity.Help;
import com.supportsys.entity.Status;
import com.supportsys.entity.SupportUser;
import com.supportsys.entity.TypeHelp;
import com.supportsys.entity.User;
import com.supportsys.repo.HelpRepo;

public class HelpModel {


	public boolean updateItem(JSONObject jsonItems) throws JSONException
	{
		EntityManager em = getEm();

		String itemHash = jsonItems.get("hashItem").toString();
		Integer itemId = Integer.parseInt(jsonItems.get("idItem").toString());

		boolean checkItem = new HelpRepo().checkIdAndHash(itemId, itemHash);

		if(checkItem == true) {

			//idUserSupport integer
			Object supportUserSet =  Integer.parseInt(jsonItems.get("supportUser").toString());
			//integer
			Object selectedStatus = Integer.parseInt(jsonItems.get("statusHelp").toString());
			String textSolution = jsonItems.get("setTxt").toString();
			Boolean canceled = Boolean.parseBoolean(jsonItems.get("canceled").toString());

			//tirar da model esta validação
			if(canceled == true) {
				textSolution = "<span style='color:#f00; font-wight:bold'>CANCELADO:</span> " + textSolution;
			}

			Status selectedStatusId = em.find(Status.class, selectedStatus);
			SupportUser supportUserId = em.find(SupportUser.class, supportUserSet);

			//Update ok
			Help helpUpdt = em.find(Help.class, itemId);
			em.clear();

			helpUpdt.setId(itemId);
			helpUpdt.setSupportUser(supportUserId);
			helpUpdt.setStatusBean(selectedStatusId);
			helpUpdt.setSolutionTxt(textSolution);

			em.getTransaction().begin();
			helpUpdt = em.merge(helpUpdt);
			em.getTransaction().commit();

		}

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

			if(idTransAction == idTransAction) {
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
	public List<Help> list(String status)
	{
		EntityManager em = getEm();
		Integer typeStatus = 0;

		switch (status) {
		case "opened":
			typeStatus = 1;
			break;
		case "exec":
			typeStatus = 2;
			break;
		case "done":
			typeStatus = 3;
			break;
		case "canceled":
			typeStatus = 4;
			break;
		default:
			typeStatus = 0;
			break;
		}

		if(typeStatus != 0) {
			Status statusObj = em.find(Status.class, typeStatus);
			List<Help> helpList = new HelpRepo().getListItemstatus(statusObj);
			em.close();
			return helpList;

		} else {
			List<Help> helpListFull = listItems();
			em.close();
			return helpListFull;
		}
	}

	/**
	 * get full items status types
	 * @return
	 */
	public List<Help> listItems()
	{
		EntityManager em = getEm();
		List<Help> fullItems = em.createNamedQuery("Help.findAll").getResultList();
		em.close();

		return fullItems;
	}

	/**
	 *
	 * @return
	 */
	public List<TypeHelp> getTypes()
	{
		EntityManager em = getEm();
		List<TypeHelp> types = em.createNamedQuery("TypeHelp.findAll").getResultList();
		em.close();

		return types;
	}

	/**
	 *
	 * @return
	 */
	public List<Status> getStatus()
	{
		EntityManager em = getEm();
		List<Status> statusTypes = em.createNamedQuery("Status.findAll").getResultList();
		em.close();

		return statusTypes;
	}


	public List<SupportUser> getSupportUsers()
	{
		EntityManager em = getEm();
		List<SupportUser> supportUsersList = em.createNamedQuery("SupportUser.findAll").getResultList();
		em.close();

		return supportUsersList;
	}


	/**
	 * Get list of departments from client
	 * @param clientId
	 * @return
	 */
	public List<Department> getDepartment(Integer clientId)
	{
		EntityManager em = getEm();

		CriteriaBuilder criteriaSet = em.getCriteriaBuilder();
		CriteriaQuery<Department> departmentData;
		Root<Department> department;

		departmentData = criteriaSet.createQuery(Department.class);
		department = departmentData.from(Department.class);
		departmentData.select(department).where(
				criteriaSet.equal(department.get("clientBean"), clientId)
				);

		List<Department> dataDepartment = em.createQuery(departmentData).getResultList();
		em.close();

		return dataDepartment;
	}

	public List<Help> openHelp(Integer idHelp, String hashItem)
	{

		EntityManager em = getEm();

		CriteriaBuilder criteriaSet = em.getCriteriaBuilder();
		CriteriaQuery<Help> helpData;
		Root<Help> help;

		helpData = criteriaSet.createQuery(Help.class);
		help = helpData.from(Help.class);
		helpData.select(help).where(
				criteriaSet.equal(help.get("id"), idHelp),
				criteriaSet.equal(help.get("hashSecure"), hashItem)
				);

		List<Help> dataHelp = em.createQuery(helpData).getResultList();
		em.close();

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
