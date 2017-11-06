package com.supportsys.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.supportsys.entity.ChatHelp;
import com.supportsys.repo.ChatRepo;
import com.supportsys.repo.HelpRepo;

public class ChatModel {

	public boolean authChat(Integer idItem, String hashItem) {
		try {
			boolean chatOk = new HelpRepo().checkIdAndHash(idItem, hashItem);
			return chatOk;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean initChat() throws IOException {

		// structure example
		/*
		 * <chat> <userMsg id="1"> <firstname>Miguel</firstname> <lastname>C</lastname>
		 * <avatar>4cd6mbn54.jpg</avatar> <msg>Analista demorando para chegar</msg>
		 * <date>04/10/2017 18:27</date> </userMsg> </chat>
		 */

		//check chat file exists

		File f = new File("/var/www/java/supportSys/WebContent/WEB-INF/xml-chat-logs/CHAT-OK.xml");
		if(f.exists() && !f.isDirectory()) {

			System.out.println("Chat já iniciado::");
		}else {

			System.out.println("Iniciando chat::");
		}

		Element chat = new Element("chat");
		Document makeChat = new Document(chat);

		int chatTest = 20;

		for(int i = 0; i<=chatTest;i++) {

			Element userMsg = new Element("userMsg");
			userMsg.setAttribute(new Attribute("id", "" +i+ ""));
			userMsg.addContent(new Element("firstName").setText("Miguel"));
			userMsg.addContent(new Element("lastName").setText("C"));
			userMsg.addContent(new Element("avatar").setText("3.jpg"));
			userMsg.addContent(new Element("msg").setText("Depois do almoço passa aqui"));
			userMsg.addContent(new Element("date").setText("04/10/2017 18:27"));
			makeChat.getRootElement().addContent(userMsg);
		}





		XMLOutputter makeChatFile = new XMLOutputter();

		makeChatFile.setFormat(Format.getPrettyFormat());
		makeChatFile.output(makeChat, new FileWriter("/var/www/java/supportSys/WebContent/WEB-INF/xml-chat-logs/CHAT-OK.xml"));

		System.out.println("Arquivo XML OK");

		return true;
	}

	public List<ChatHelp> getChat(Integer idItem) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("support");
		EntityManager em = emf.createEntityManager();

		List<ChatHelp> fullChat = new ChatRepo().getChat(em, idItem);
		emf.close();

		return fullChat;
	}

}
