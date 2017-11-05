package com.supportsys.model;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
	public User authUser(JSONObject userData) throws JSONException, IOException
	{
		//Data received json
		Object userEmail = userData.get("email").toString();
		Object password = userData.get("pass").toString();

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("support");
		EntityManager em = emf.createEntityManager();

		//Exec database validation data
		try {

			//Set criteria to query
//			CriteriaBuilder criteriaSet = em.getCriteriaBuilder();
//			CriteriaQuery<User> UserData;
//			Root<User> user;
//
//			UserData = criteriaSet.createQuery(User.class);
//			user = UserData.from(User.class);
//			UserData.select(user).where(
//					criteriaSet.equal(user.get("email"), userEmail),
//					criteriaSet.equal(user.get("pass"), password)
//					);

			String query = "SELECT u FROM User u WHERE u.email= :email AND u.pass= :pass";

			//User dataUser = em.createQuery(UserData).getSingleResult();
			User dataUser = em.createQuery(query, User.class).
					setParameter("email", userEmail).
					setParameter("pass", password).
					getSingleResult();
			emf.close();

			String execLoginResult = Integer.toString(dataUser.getId());

			if(execLoginResult != "" || execLoginResult != null)
			{
				return dataUser;

			}else {
				dataUser = null;
				return dataUser;
			}

		} catch (Exception e) {

			return null;
		}
	}
}
