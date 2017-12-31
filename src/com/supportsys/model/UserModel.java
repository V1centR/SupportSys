package com.supportsys.model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.json.JSONException;
import org.json.JSONObject;

import com.supportsys.entity.Client;
import com.supportsys.entity.Department;
import com.supportsys.entity.Image;
import com.supportsys.entity.User;
import com.supportsys.entity.UserGroup;
import com.supportsys.repo.ClientRepo;
import com.supportsys.utilities.EmailUtilities;
import com.supportsys.utilities.SecurityUtilities;

public class UserModel extends EmModel{

	/**
	 * Return full list of users
	 * @return
	 */
	public List<User> getAllUsers()
	{
		EntityManager em = super.getEm();

		List<User> usersFull = em.createNamedQuery("User.findAll").getResultList();
		em.close();

		return usersFull;
	}

	/**
	 * Return full list of Clients
	 * @return
	 */
	public List<Client> getAllClients()
	{
		List<Client> fullList = new ClientRepo().getAllClients();

		return fullList;
	}

	/**
	 * Add user method
	 * @param jsonItems
	 * @return boolean
	 * @throws JSONException
	 */
	public boolean addUser(JSONObject jsonItems) throws JSONException
	{
		EntityManager em = super.getEm();

		boolean editSecOk = false;
		Integer setAvatarId = 0;
		//TODO autenticar transação
		String idItem = jsonItems.get("idItem").toString();
		String hashItem = jsonItems.get("hashItem").toString();

		String nomeUser = jsonItems.get("nameUser").toString();
		String sNameUser = jsonItems.get("sNameUser").toString();
		String emailUser = jsonItems.get("emailUser").toString();
		String gender = jsonItems.get("gender").toString();
		String selectClient = jsonItems.get("selectClient").toString();
		Integer department = Integer.parseInt(jsonItems.get("department").toString());
		Integer userGroup = Integer.parseInt(jsonItems.get("userGroup").toString());
		String hashSec = jsonItems.get("hashSec").toString();
		String resetPassword = jsonItems.get("resetPassword").toString();
		Integer setActive = Integer.parseInt(jsonItems.get("active").toString());

		long now = Calendar.getInstance().getTimeInMillis();
		Timestamp tsNow = new Timestamp(now);

		Department departmentObj = em.find(Department.class, department);
		UserGroup userGroupObj = em.find(UserGroup.class, userGroup);

		if(!hashSec.isEmpty()) {
			editSecOk = checkEmailAndHash(emailUser, hashSec);
		} else {
			editSecOk = false;
		}

		boolean emailOk = checkEmail(emailUser);

		System.out.println("Check checkEmailExists:: " + emailOk);
		System.out.println("Check nomeUser:: " + nomeUser);
		System.out.println("Check sNameUser:: " + sNameUser);
		System.out.println("Check emailUser:: " + emailUser);
		System.out.println("Check gender:: " + gender);
		System.out.println("Check selectClient:: " + selectClient);
		System.out.println("Check department:: " + department);
		System.out.println("Check userGroup:: " + userGroup);

		try {

			if(emailOk == true)
			{
				String sha1ToUser = "";
				String defaultPass = "";

				sha1ToUser = new SecurityUtilities().makeSha1(tsNow.toString());
				defaultPass = new SecurityUtilities().makeSha1("123123");

				System.out.println("Password generated:: " + defaultPass);

				if(gender.equals("F"))
				{
					setAvatarId = 1;
				} else {
					setAvatarId = 2;
				}

				Image defaultImage = em.find(Image.class, setAvatarId);

				User addUser = new User();

				addUser.setName(nomeUser);
				addUser.setSname(sNameUser);
				addUser.setGender(gender);
				addUser.setEmail(emailUser);
				addUser.setDepartment(departmentObj);
				//addUser.setDescription(null);
				addUser.setIdConfEmail(sha1ToUser);
				addUser.setImage(defaultImage);
				//addUser.setMobile(null);
				//addUser.setPhone(null4);
				addUser.setPass(defaultPass);
				addUser.setUserGroupBean(userGroupObj);
				addUser.setDataRegister(tsNow);
				addUser.setActive(1);

				em.getTransaction().begin();
				em.persist(addUser);
				em.getTransaction().commit();
				em.close();

				return true;

			} else {

				return false;
			}

		} catch (Exception e) {

			System.out.println(e);

			return false;
		}

	}

	/**
	 * Add user method
	 * @param jsonItems
	 * @return boolean
	 * @throws JSONException
	 */
	public boolean editUser(JSONObject jsonItems) throws JSONException
	{
		EntityManager em = super.getEm();

		boolean editSecOk = false;
		Integer setAvatarId = 0;
		//TODO autenticar transação
		String idItem = jsonItems.get("idItem").toString();
		String hashItem = jsonItems.get("hashItem").toString();

		String nomeUser = jsonItems.get("nameUser").toString();
		String sNameUser = jsonItems.get("sNameUser").toString();
		String emailUser = jsonItems.get("emailUser").toString();
		String gender = jsonItems.get("gender").toString();
		String selectClient = jsonItems.get("selectClient").toString();
		Integer department = Integer.parseInt(jsonItems.get("department").toString());
		Integer userGroup = Integer.parseInt(jsonItems.get("userGroup").toString());
		String hashSec = jsonItems.get("hashSec").toString();
		String resetPassword = jsonItems.get("resetPassword").toString();
		Integer setActive = Integer.parseInt(jsonItems.get("active").toString());

		long now = Calendar.getInstance().getTimeInMillis();
		Timestamp tsNow = new Timestamp(now);

		Department departmentObj = 	em.find(Department.class, department);
		UserGroup userGroupObj = 	em.find(UserGroup.class, userGroup);

		if(!hashSec.isEmpty()) {
			editSecOk = checkEmailAndHash(emailUser, hashSec);
		} else {
			editSecOk = false;
		}

		try {

			if(editSecOk == true){

				User addUser = em.find(User.class, Integer.parseInt(idItem));
				em.clear();

				if(resetPassword.equals("true"))
				{
					String defaultPass = "";
					defaultPass = new SecurityUtilities().makeSha1("123123");
					addUser.setPass(defaultPass);
					//TODO send email to user
				}

				addUser.setName(nomeUser);
				addUser.setSname(sNameUser);
				//addUser.setGender(gender);
				//addUser.setEmail(emailUser);
				addUser.setDepartment(departmentObj);
				//addUser.setDescription(null);
				//addUser.setIdConfEmail(sha1ToUser);
				//addUser.setImage(defaultImage);
				//addUser.setMobile(null);
				//addUser.setPhone(null4);
				//addUser.setPass("123123");
				addUser.setUserGroupBean(userGroupObj);
				//addUser.setDataRegister(tsNow);
				addUser.setActive(setActive);

				em.getTransaction().begin();
				addUser = em.merge(addUser);
				em.getTransaction().commit();
				em.close();

				return true;

			} else {

				return false;
			}

		} catch (Exception e) {

			System.out.println(e);

			return false;
		}
	}

	/**
	 * Check email and idConfEmail
	 * @return
	 */
	public boolean checkEmailAndHash(String email, String hashCode)
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("support");
		EntityManager em = emf.createEntityManager();

		String query = "SELECT u FROM User u WHERE u.email= :email AND u.idConfEmail = :hashCode";
		List<User> itemData = em.createQuery(query, User.class).
				setParameter("email", email).
				setParameter("hashCode", hashCode).
				getResultList();
		emf.close();

		if(itemData.isEmpty())
		{
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Check email exists
	 * @return
	 */
	public boolean checkEmail(String email)
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("support");
		EntityManager em = emf.createEntityManager();

		//validate email
		boolean emailCheck = new EmailUtilities().checkEmail(email);

		if(emailCheck)
		{
			String query = "SELECT u FROM User u WHERE u.email= :email";
			List<User> itemData = em.createQuery(query, User.class).
			setParameter("email", email).
			getResultList();
			emf.close();

			if(itemData.isEmpty())
			{
				return true;
			} else {
				return false;
			}

		} else {

			return false;
		}
	}


	/**
	 * List al clients
	 * @return List<Client>
	 */
	public List<Client> getClients()
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("support");
		EntityManager em = emf.createEntityManager();

		List<Client> fullClients = em.createNamedQuery("Client.findAll").getResultList();
		emf.close();

		return fullClients;
	}

	/**
	 * Get list all user groups
	 * @return List<Client>
	 */
	public List<UserGroup> getGroups()
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("support");
		EntityManager em = emf.createEntityManager();

		List<UserGroup> fullGroups = em.createNamedQuery("UserGroup.findAll").getResultList();
		emf.close();

		return fullGroups;
	}


	/**
	 * Get departments by client ID
	 * @param clientId
	 * @return
	 */
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
