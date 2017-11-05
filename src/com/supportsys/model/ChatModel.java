package com.supportsys.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.supportsys.entity.ChatHelp;
import com.supportsys.repo.ChatRepo;
import com.supportsys.repo.HelpRepo;

public class ChatModel {

	public List<ChatHelp> authChat(Integer idItem, String hashItem)
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("support");
		EntityManager em = emf.createEntityManager();

		try {

			boolean fullChat = new HelpRepo().checkIdAndHash(idItem, hashItem);

			List<ChatHelp> chatContent = em.createNamedQuery("ChatHelp.findAll").getResultList();
			emf.close();
			return chatContent;

		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public List<ChatHelp> getChat(Integer idItem) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("support");
		EntityManager em = emf.createEntityManager();

		List<ChatHelp> fullChat = new ChatRepo().getChat(em, idItem);
		emf.close();

		return fullChat;
	}

}
