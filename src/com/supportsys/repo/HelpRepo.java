package com.supportsys.repo;

import java.util.List;

import javax.persistence.EntityManager;

import com.supportsys.entity.Help;
import com.supportsys.entity.Status;
import com.supportsys.model.EmModel;

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
	 * Get list items by status
	 * @param hashCode
	 * @return
	 */
	public List<Help> getListItemstatus(Status statusId)
	{

		EntityManager em = new EmModel().getEm();
		String query = "SELECT h FROM Help h WHERE h.statusBean= :status";
		List<Help> itemObj = em.createQuery(query, Help.class).
		setParameter("status", statusId).
		getResultList();
		em.close();

		return itemObj;

	}

}
