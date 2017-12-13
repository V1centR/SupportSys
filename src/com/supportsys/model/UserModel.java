package com.supportsys.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

public class UserModel {


	/**
	 * Return full list of users
	 * @return
	 */
	public List<User> getAllUsers()
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("support");
		EntityManager em = emf.createEntityManager();

		List<User> usersFull = em.createNamedQuery("User.findAll").getResultList();
		emf.close();

		return usersFull;
	}

	/**
	 * Return full list of Clients
	 * @return
	 */
	public List<Client> getAllClients()
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("support");
		EntityManager em = emf.createEntityManager();

		List<Client> fullList = em.createNamedQuery("Client.findAll").getResultList();
		emf.close();

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
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("support");
		EntityManager em = emf.createEntityManager();

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

			if(editSecOk == true){

				User addUser = em.find(User.class, Integer.parseInt(idItem));
				em.clear();

				addUser.setName(nomeUser);
				addUser.setSname(sNameUser);
				addUser.setGender(gender);
				addUser.setEmail(emailUser);
				addUser.setDepartment(departmentObj);
				//addUser.setDescription(null);
				//addUser.setIdConfEmail(sha1ToUser);
				//addUser.setImage(defaultImage);
				//addUser.setMobile(null);
				//addUser.setPhone(null4);
				addUser.setPass("123123");
				addUser.setUserGroupBean(userGroupObj);
				addUser.setDataRegister(tsNow);

				em.getTransaction().begin();
				addUser = em.merge(addUser);
				em.getTransaction().commit();
				emf.close();

				return true;


			} else {


				if(emailOk == true)
				{

					String sha1ToUser = "";
					MessageDigest crypt = MessageDigest.getInstance("sha1");
					crypt.reset();
					crypt.update(tsNow.toString().getBytes("utf8"));
					sha1ToUser = String.format("%40x", new BigInteger(1,crypt.digest()));

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
					addUser.setPass("123123");
					addUser.setUserGroupBean(userGroupObj);
					addUser.setDataRegister(tsNow);

					em.getTransaction().begin();
					em.persist(addUser);
					em.getTransaction().commit();
					emf.close();

					return true;

				} else {

					return false;
				}
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
		String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);

		if(matcher.matches())
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
