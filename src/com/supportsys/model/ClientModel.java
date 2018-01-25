package com.supportsys.model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import org.json.JSONException;
import org.json.JSONObject;

import com.supportsys.entity.Client;
import com.supportsys.entity.Image;
import com.supportsys.entity.Uf;
import com.supportsys.utilities.EmailUtilities;
import com.supportsys.utilities.SecurityUtilities;

public class ClientModel extends EmModel {


	/**
	 * Add Client method
	 * @param jsonItems
	 * @return boolean
	 * @throws JSONException
	 */
	public boolean addClient(JSONObject jsonItems) throws JSONException
	{
		EntityManager em = super.getEm();
		SecurityUtilities secUtils = null;

		boolean editSecOk = false;
		Integer setAvatarId = 1;

		//TODO autenticar transação
		String idItem = jsonItems.get("idItem").toString();
		//String hashItem = jsonItems.get("hashItem").toString();
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
		String nameClient 		= jsonItems.get("clientName").toString();
		String addressClient 	= jsonItems.get("addressClient").toString();
		String districtClient 	= jsonItems.get("districtClient").toString();
		String clientCity 		= jsonItems.get("clientCity").toString();
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

		Uf stateClientObj = em.find(Uf.class, selectState);

		System.out.println("UF object:: " + stateClientObj);

		boolean emailOk = new EmailUtilities().checkEmail(emailClient);

		System.out.println("Check checkEmailExists:: " + emailOk);

		try {

			String sha1ToUser = "";
			String defaultPass = "";

//			sha1ToUser = secUtils.makeSha1(tsNow.toString());
//			defaultPass = secUtils.makeSha1("123123");
//
//			System.out.println("Password generated:: " + defaultPass);

			Image defaultImage = em.find(Image.class, setAvatarId);

			Client addClient = new Client();

			addClient.setName(nameClient);
			addClient.setAddress(addressClient);
			addClient.setBairro(districtClient);
			addClient.setCity(clientCity);
			addClient.setUfBean(stateClientObj);
			addClient.setCnpj(cnpjClient);
			addClient.setPhone(phoneClient);
			addClient.setImage(defaultImage);
			addClient.setEmail(emailClient);
			addClient.setLevel(levelSelect);
			addClient.setActive(setActive);
			addClient.setDescription(description);
			//addClient.setDataRegister(tsNow);

			em.getTransaction().begin();
			em.persist(addClient);
			em.getTransaction().commit();
			em.close();

			return true;

		} catch (Exception e) {

			System.out.println(e);

			return false;
		}
	}


	/**
	 * Add Client method
	 * @param jsonItems
	 * @return boolean
	 * @throws JSONException
	 */
	public boolean updateClient(JSONObject jsonItems) throws JSONException
	{
		EntityManager em = super.getEm();
		SecurityUtilities secUtils = null;

		boolean editSecOk = false;
		Integer setAvatarId = 1;

		//TODO autenticar transação
		String idItem = jsonItems.get("idItem").toString();

		Client addClient = em.find(Client.class, Integer.parseInt(idItem));
		em.clear();

		//String hashItem = jsonItems.get("hashItem").toString();
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
		String nameClient 		= jsonItems.get("clientName").toString();
		String addressClient 	= jsonItems.get("addressClient").toString();
		String districtClient 	= jsonItems.get("districtClient").toString();
		String clientCity 		= jsonItems.get("clientCity").toString();
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

		Uf stateClientObj = em.find(Uf.class, selectState);

		System.out.println("UF object:: " + stateClientObj);

		boolean emailOk = new EmailUtilities().checkEmail(emailClient);

		System.out.println("Check checkEmailExists:: " + emailOk);

		try {

			String sha1ToUser = "";
			String defaultPass = "";

//			sha1ToUser = secUtils.makeSha1(tsNow.toString());
//			defaultPass = secUtils.makeSha1("123123");
//
//			System.out.println("Password generated:: " + defaultPass);

			Image defaultImage = em.find(Image.class, setAvatarId);

			addClient.setName(nameClient);
			addClient.setAddress(addressClient);
			addClient.setBairro(districtClient);
			addClient.setCity(clientCity);
			addClient.setUfBean(stateClientObj);
			addClient.setCnpj(cnpjClient);
			addClient.setPhone(phoneClient);
			addClient.setImage(defaultImage);
			addClient.setEmail(emailClient);
			addClient.setLevel(levelSelect);
			addClient.setActive(setActive);
			addClient.setDescription(description);
			//addClient.setDataRegister(tsNow);

			em.getTransaction().begin();
			em.merge(addClient);
			em.getTransaction().commit();
			em.close();

			return true;

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
