package com.supportsys.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

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

	public boolean initChat(String hashItem) throws IOException, JDOMException {

		// structure example
		/*
		 * <chat> <userMsg id="1"> <firstname>Miguel</firstname> <lastname>C</lastname>
		 * <avatar>4cd6mbn54.jpg</avatar> <msg>Analista demorando para chegar</msg>
		 * <date>04/10/2017 18:27</date> </userMsg> </chat>
		 */

		//check chat file exists
		File f = new File("/var/www/java/supportSys/WebContent/WEB-INF/xml-chat-logs/"+hashItem+".xml");
		if(f.exists() && !f.isDirectory()) {

			System.out.println("Chat já iniciado::");

			boolean update = updateChat(hashItem);

		}else {

			System.out.println("Iniciando chat::");

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
			makeChatFile.output(makeChat, new FileWriter("/var/www/java/supportSys/WebContent/WEB-INF/xml-chat-logs/"+hashItem+".xml"));

			System.out.println("Arquivo "+hashItem+".xml gerado com sucesso!");
		}



		return true;
	}

	public boolean updateChat(String hashItem) throws JDOMException, IOException
	{
		/*
		 <chat>
		  <userMsg id="0">
		    <firstName>Miguel</firstName>
		    <lastName>C</lastName>
		    <avatar>3.jpg</avatar>
		    <msg>Depois do almoço passa aqui</msg>
		    <date>04/10/2017 18:27</date>
		  </userMsg>
		  </chat>*/



		String path = "/var/www/java/supportSys/WebContent/WEB-INF/xml-chat-logs/";

		//get file
		File chatXMLFile = new File(path+hashItem+".xml");
		System.out.println("Aquivo carregado:: " + path+hashItem+".xml");
		SAXBuilder saxBuilder = new SAXBuilder();
		Document doc = saxBuilder.build(chatXMLFile);
		Element rootElement = doc.getRootElement();
		//##########################

		List<Element> docElements = rootElement.getChildren("userMsg");


		String nextMsgId = Integer.toString(docElements.size());

		System.out.println("Tamanho do chat::" + nextMsgId);

		Element userMsg = new Element("userMsg");
		userMsg.setAttribute(new Attribute("id", nextMsgId));
		userMsg.addContent(new Element("firstName").setText("Otávio"));
		userMsg.addContent(new Element("lastName").setText("Evangelista"));
		userMsg.addContent(new Element("avatar").setText("3.jpg"));
		userMsg.addContent(new Element("msg").setText("Ok 5min eu chego"));
		userMsg.addContent(new Element("date").setText("04/10/2017 13:35"));
		rootElement.addContent(userMsg);


		XMLOutputter makeChatFile = new XMLOutputter();

		makeChatFile.setFormat(Format.getPrettyFormat());
		makeChatFile.output(doc, new FileWriter("/var/www/java/supportSys/WebContent/WEB-INF/xml-chat-logs/"+hashItem+".xml"));

		System.out.println("Chat atualizado::" + nextMsgId);

		return true;
	}

}
