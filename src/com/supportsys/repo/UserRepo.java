package com.supportsys.repo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.supportsys.entity.User;

public class UserRepo {

	public User getUserInfo(Integer idUser)
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("support");
		EntityManager em = emf.createEntityManager();

		User userData = em.find(User.class, idUser);
		emf.close();

		return userData;
	}
}
