package com.supportsys.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.json.JSONException;
import org.junit.Test;

import com.supportsys.controller.UserController;
import com.supportsys.entity.User;
import com.supportsys.model.UserModel;

public class AddUserTest {

	@Test
	public void addUsersTest() throws JSONException {

		Integer numberOfUser = 10;

		String jsonItems = "";
		String gender = "";


		for(Integer i=0; i<=numberOfUser;i++) {

			if(i%2 == 0)
			{
				gender = "F";
			}else {

				gender = "M";
			}

			jsonItems = "{\"hashSec\":\"\",\"hashItem\":\"undefined\",\"nameUser\":\"User"+ i +" \",\"sNameUser\":\"Sobrenome\",\"gender\":\""+gender+"\",\"emailUser\":\"user"+i+"@weg.com\",\"selectClient\":\"4\",\"department\":\"29\",\"userGroup\":\"1\",\"idItem\":\"\"}";

			Integer newUser = new UserController().execUserAdd(jsonItems, null);

		}
	}

	/**
	 *
	 * @throws JSONException
	 */
	@Test
	public void updateUsersTest() throws JSONException {

		String jsonItems = "";
		String gender = "";
		Integer i = 0;
		Integer confirmValue = 201;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("support");
		EntityManager em = emf.createEntityManager();

		List<User> userList = new UserModel().getAllUsers();

		for(User userData: userList)
		{
			if(i%2 == 0)
			{
				gender = "F";
			}else {

				gender = "M";
			}
			jsonItems = "{\"hashSec\":\""+ userData.getIdConfEmail() +"\",\"hashItem\":\"undefined\",\"nameUser\":\""+ userData.getName() +" \",\"sNameUser\":\"jUnit 4.1\",\"gender\":\""+userData.getGender()+"\",\"emailUser\":\""+userData.getEmail()+"\",\"selectClient\":\""+userData.getDepartment().getClientBean().getId()+"\",\"department\":\""+userData.getDepartment().getId()+"\",\"userGroup\":\""+userData.getUserGroupBean().getId()+"\",\"idItem\":\""+userData.getId()+"\"}";

			Integer newUser = new UserController().execUserAdd(jsonItems, null);

			//confirmValue.equals(newUser);
			assertEquals(confirmValue, newUser);

			i++;

		}
	}

}
