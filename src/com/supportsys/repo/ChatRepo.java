package com.supportsys.repo;

import java.util.List;

import javax.persistence.EntityManager;

import com.supportsys.entity.ChatHelp;
import com.supportsys.entity.Help;

public class ChatRepo {


	public List<ChatHelp> getChat(EntityManager em, Integer idItem)
	{
		try {

			Help helpItemObj = em.find(Help.class, idItem);
			String query = "SELECT c FROM ChatHelp c WHERE c.help= :id";

			List<ChatHelp> fullChat = em.createQuery(query, ChatHelp.class).
					setParameter("id", helpItemObj).getResultList();

			return fullChat;

		} catch (Exception e) {
			return null;
		}
	}

}
