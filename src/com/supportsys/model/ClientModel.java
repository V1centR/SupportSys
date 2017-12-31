package com.supportsys.model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import org.json.JSONException;
import org.json.JSONObject;

import com.supportsys.entity.Client;
import com.supportsys.entity.Department;
import com.supportsys.entity.Image;
import com.supportsys.entity.Uf;
import com.supportsys.entity.User;
import com.supportsys.entity.UserGroup;
import com.supportsys.utilities.EmailUtilities;
import com.supportsys.utilities.SecurityUtilities;

public class ClientModel extends EmModel {


	/**
	 * Add user method
	 * @param jsonItems
	 * @return boolean
	 * @throws JSONException
	 */
	public boolean addClient(JSONObject jsonItems) throws JSONException
	{
		EntityManager em = super.getEm();
		SecurityUtilities secUtils = null;

		boolean editSecOk = false;
		Integer setAvatarId = 0;
		//TODO autenticar transação
		String idItem = jsonItems.get("idItem").toString();
		String hashItem = jsonItems.get("hashItem").toString();
		/*
      	nameClient
		addressClient
		districtClient
		selectState
		cnpjClient
		phoneClient
		logoImage
		emailClient
		levelSelect
		active
		resetPassword
		description
	*/
		String nameClient 		= jsonItems.get("nameClient").toString();
		String addressClient 	= jsonItems.get("addressClient").toString();
		String districtClient 	= jsonItems.get("districtClient").toString();
		Integer selectState 	= Integer.parseInt(jsonItems.get("selectState").toString());
		String cnpjClient 		= jsonItems.get("cnpjClient").toString();
		String phoneClient 		= jsonItems.get("phoneClient").toString();

		//make function upload validate and record image
		String logoImage 		= jsonItems.get("logoImage").toString();
		String emailClient 		= jsonItems.get("emailClient").toString();

		Integer levelSelect 	= Integer.parseInt(jsonItems.get("levelSelect").toString());
		Integer setActive 		= Integer.parseInt(jsonItems.get("active").toString());
		Integer resetPassword 	= Integer.parseInt(jsonItems.get("resetPassword").toString());
		String description 		= jsonItems.get("description").toString();


		long now = Calendar.getInstance().getTimeInMillis();
		Timestamp tsNow = new Timestamp(now);

		Uf stateClientObj = em.find(Uf.class, districtClient);

		boolean emailOk = new EmailUtilities().checkEmail(emailClient);

		System.out.println("Check checkEmailExists:: " + emailOk);


		try {

			if(emailOk == true)
			{
				String sha1ToUser = "";
				String defaultPass = "";

				sha1ToUser = secUtils.makeSha1(tsNow.toString());
				defaultPass = secUtils.makeSha1("123123");

				System.out.println("Password generated:: " + defaultPass);

				Image defaultImage = em.find(Image.class, setAvatarId);

				Client addClient = new Client();

				addClient.setName(nameClient);
				addClient.setAddress(addressClient);
				addClient.setBairro(districtClient);
				addClient.setUfBean(stateClientObj);
				//addClient.setName(cnpjClient);
				addClient.setPhone(phoneClient);
//				addClient.setName(logoImage);
				addClient.setEmail(emailClient);
				addClient.setImage(defaultImage);
				addClient.setLevel(levelSelect);
				addClient.setActive(setActive);
				addClient.setDesc(description);
				//addClient.setDataRegister(tsNow);
				addClient.setActive(1);

				em.getTransaction().begin();
				em.persist(addClient);
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
	public boolean updateClient(JSONObject jsonItems) throws JSONException
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
			editSecOk = new EmailUtilities().checkEmail(emailUser);
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
	 * Get List all states sign
	 * @return List<Client>
	 */
	public List<Uf> getStates()
	{
		EntityManager em = super.getEm();

		List<Uf> fullStates = em.createNamedQuery("Uf.findAll").getResultList();
		em.close();

		return fullStates;
	}

}
