package com.supportsys.model;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.json.JSONException;
import org.json.JSONObject;

import com.supportsys.entity.User;

/**
 * 
 * @author vicentcdb@gmail.com
 * Class login, logout users
 *
 */
public class AuthModel {

	/**
	 * Exec user authentication, data receive via Json post
	 * @param userData
	 * @return
	 * @throws JSONException
	 */
	public List<User> authUser(JSONObject userData) throws JSONException, IOException
	{
		//Data received json 
		Object userEmail = userData.get("email").toString();
		Object password = userData.get("pass").toString();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("support");
		EntityManager em = emf.createEntityManager();
		
		//Exec database validation data
		try {
			
			//Set criteria to query
			CriteriaBuilder criteriaSet = em.getCriteriaBuilder();
			CriteriaQuery<User> UserData;
			Root<User> user;
			
			UserData = criteriaSet.createQuery(User.class);
			user = UserData.from(User.class);
			UserData.select(user).where(
					criteriaSet.equal(user.get("email"), userEmail),
					criteriaSet.equal(user.get("pass"), password)
					);
			
			List<User> dataUser = em.createQuery(UserData).getResultList();
			int execLoginResult = dataUser.size();
			
//			for(User itemData: dataUser) {
//				System.out.println("Nome do banco:: " + itemData.getName());
//			}
			
			if(execLoginResult == 1)
			{
				return dataUser;
			}else {
				dataUser = null;
				return dataUser;
			}
			
		} catch (Exception e) {
			List<User> dataUser = null;
			return dataUser;
		}
	}
}
