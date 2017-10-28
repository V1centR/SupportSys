package com.supportsys.repo;

import com.supportsys.entity.Help;
import com.supportsys.model.EmModel;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.annotations.NamedQuery;

/**
 * 
 * @author Vicente Ramos Carvalho vicentcdb@gmail.com
 *
 */
public class HelpRepo {

	/**
	 * Check ID + HashCode of Item
	 * @param id
	 * @param hashCode
	 * @return
	 */
	public boolean checkIdAndHash(Integer id, String hashCode)
	{
		try {
			
			EntityManager em = new EmModel().getEm();
			String query = "SELECT h FROM Help h WHERE h.id= :id AND h.hashSecure= :hashCode";
			//optimize this query
			Help itemChecked = em.createQuery(query, Help.class).
			setParameter("id", id).
			setParameter("hashCode", hashCode).
			getSingleResult();
			
			em.close();
			return true;
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
	
	
	/**
	 * Get literal item Help
	 * @param id
	 * @param hashCode
	 * @return
	 */
//	public Help getItem(Integer id, String hashCode)
//	{
//		EntityManager em = new EmModel().getEm();
//		String query = "SELECT h FROM Help h WHERE h.id= :id AND h.hashSecure= :hashCode";
//		//optimize this query
//		Help itemObj = em.createQuery(query, Help.class).
//		setParameter("id", id).
//		setParameter("hashCode", hashCode).
//		getSingleResult();
//		
//		em.close();
//		
//		return itemObj;
//		
//	}
	
}
