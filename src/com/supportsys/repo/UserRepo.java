package com.supportsys.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.supportsys.entity.Client;
import com.supportsys.entity.User;

public class UserRepo {

	/**
	 *
	 * @param idUser
	 * @return
	 */
	public User getUserInfo(Integer idUser)
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("support");
		EntityManager em = emf.createEntityManager();

		User userData = em.find(User.class, idUser);
		emf.close();

		return userData;
	}

	/**
	 * Get All users by client ID
	 * @param clientId
	 * @return
	 */
	public List<Object[]> getUsersByClient(Integer idClient)
	{

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("support");
		EntityManager em = emf.createEntityManager();

		try {
				Client clientObj = em.find(Client.class, idClient);
				System.out.println("Client Obj:: " + clientObj);
				String query = "SELECT u.id,u.name,u.sname,u.email,u.department,u.image,CONCAT(i.id,'.',i.ext) as imageUser,u.userGroupBean,d.name as deptUser,g.name as groupUser,d.clientBean,c.name,c.id as clientId FROM User u INNER JOIN UserGroup g ON u.userGroupBean = g.id INNER JOIN Image i ON u.image = i.id INNER JOIN Department d ON u.department = d.id INNER JOIN Client c ON u.department = d.id WHERE d.clientBean = :departmentId GROUP BY u.id";
				//String query = "SELECT users.name,users.sname,users.email,users.dept,users.avatar,CONCAT(images.id,'.',images.ext) as userImage, users.userGroup,department.name as deptUser,userGroup.name as groupUser,department.client,clients.name as clientName,clients.id as clientId FROM users INNER JOIN userGroup ON users.userGroup = userGroup.id INNER JOIN images ON users.avatar = images.id INNER JOIN department ON users.dept = department.id INNER JOIN clients ON users.dept = department.id WHERE department.client = :departmentId GROUP BY users.id";

				//optimize this query
				List<Object[]> clientsByClient = em.createQuery(query).
				setParameter("departmentId", clientObj).getResultList();
				emf.close();

				return clientsByClient;

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR:: " + e);
			return null;
		}
	}


//	public List getUsersByClient2(Integer idClient)
//	{
//
//		SessionFactory sf = new Configuration().configure().buildSessionFactory();
//		Session session = sf.getCurrentSession();
//
//		String queryStr = "SELECT u.name,u.sname,u.email,u.department,u.image,i.id,i.ext,u.userGroupBean,d.name as deptUser,g.name as groupUser,d.clientBean,c.name,c.id as clientId FROM User u INNER JOIN UserGroup g ON u.userGroupBean = g.id INNER JOIN Image i ON u.image = i.id INNER JOIN Department d ON u.department = d.id INNER JOIN Client c ON u.department = d.id WHERE d.clientBean = :departmentId GROUP BY u.id";
//
//		Transaction tx = session.beginTransaction();
//		List query = session.createQuery(queryStr).setParameter("departmentId", idClient).list();
//
//
//		return query;
//
//	}

}
