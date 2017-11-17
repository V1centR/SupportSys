package com.supportsys.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

	public String initChat(String hashItem, Object idUser, String nameUser, String userAvatar, String txtMsg, String mode) throws IOException, JDOMException
	{

		//check chat file exists
		String DIRECTORY_FILES = "/var/www/java/supportSys/WebContent/WEB-INF/xml-chat-logs/";
		File f = new File(DIRECTORY_FILES+hashItem+".xml");

		Date todaysDate = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy H:mm");
		String datef = dateFormat.format(todaysDate);
		Integer setMode = Integer.parseInt(mode);


		if(f.exists() && !f.isDirectory()) {

			if(setMode == 0) {
				String chatFile = DIRECTORY_FILES+hashItem+".xml";
				return chatFile;

			} else {

				boolean update = updateChat(hashItem,idUser, nameUser, userAvatar,datef, txtMsg);
				if(update == true)
				{
					String chatFile = DIRECTORY_FILES+hashItem+".xml";
					return chatFile;
				}
			}

		}else {

			if(setMode == 0) {
				String emptyChat = "";

				return emptyChat;

			} else {

				Element chat = new Element("chat");
				Document makeChat = new Document(chat);

				Element userMsg = new Element("userMsg");
				userMsg.setAttribute(new Attribute("id", "1"));
				userMsg.addContent(new Element("statusFlag").setText("false"));
				userMsg.addContent(new Element("userName").setText(nameUser));
				userMsg.addContent(new Element("avatar").setText(userAvatar));
				userMsg.addContent(new Element("code").setText(""));
				userMsg.addContent(new Element("toUser").setText(""));
				userMsg.addContent(new Element("msg").setText(txtMsg));
				userMsg.addContent(new Element("date").setText(datef));
				userMsg.addContent(new Element("toUserName").setText(""));
				userMsg.addContent(new Element("avatarToUser").setText(""));

				makeChat.getRootElement().addContent(userMsg);

				XMLOutputter makeChatFile = new XMLOutputter();

				makeChatFile.setFormat(Format.getPrettyFormat());
				makeChatFile.output(makeChat, new FileWriter(DIRECTORY_FILES+hashItem+".xml"));

				String chatFile = DIRECTORY_FILES+hashItem+".xml";

				return chatFile;
			}


		}
		return null;

	}

	public boolean updateChat(String hashItem,Object idUser, String nameUser, String userAvatar, String dateF, String txtMsg) throws JDOMException, IOException
	{

		String path = "/var/www/java/supportSys/WebContent/WEB-INF/xml-chat-logs/";

		//get file
		File chatXMLFile = new File(path+hashItem+".xml");
		System.out.println("Aquivo carregado:: " + path+hashItem+".xml");
		SAXBuilder saxBuilder = new SAXBuilder();
		Document doc = saxBuilder.build(chatXMLFile);
		Element rootElement = doc.getRootElement();
		//##########################

		Date todaysDate = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy H:mm");
		String datef = dateFormat.format(todaysDate);

		List<Element> docElements = rootElement.getChildren("userMsg");
		String nextMsgId = Integer.toString(docElements.size());


		Element userMsg = new Element("userMsg");
		userMsg.setAttribute(new Attribute("id", nextMsgId));
		userMsg.addContent(new Element("statusFlag").setText(nameUser));
		userMsg.addContent(new Element("userName").setText(nameUser));
		userMsg.addContent(new Element("avatar").setText(userAvatar));
		userMsg.addContent(new Element("code").setText(userAvatar));
		userMsg.addContent(new Element("status").setText(userAvatar));
		userMsg.addContent(new Element("toUser").setText(userAvatar));
		userMsg.addContent(new Element("msg").setText(txtMsg));
		userMsg.addContent(new Element("date").setText(datef));
		userMsg.addContent(new Element("userName").setText(nameUser));

		rootElement.addContent(userMsg);

		XMLOutputter makeChatFile = new XMLOutputter();

		makeChatFile.setFormat(Format.getPrettyFormat());
		makeChatFile.output(doc, new FileWriter("/var/www/java/supportSys/WebContent/WEB-INF/xml-chat-logs/"+hashItem+".xml"));

		return true;
	}


	public boolean updateChatStatus(String hashItem,Object idUser, String nameUser, String userAvatar, String txtMsg, Integer statusCode) throws JDOMException, IOException
	{

		String path = "/var/www/java/supportSys/WebContent/WEB-INF/xml-chat-logs/";

		Date todaysDate = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy H:mm");
		String datef = dateFormat.format(todaysDate);

		//get file
		File chatXMLFile = new File(path+hashItem+".xml");
		System.out.println("Aquivo carregado:: " + path+hashItem+".xml");
		SAXBuilder saxBuilder = new SAXBuilder();
		Document doc = saxBuilder.build(chatXMLFile);
		Element rootElement = doc.getRootElement();
		//##########################

		String statusCodeStr = statusCode.toString();

		List<Element> docElements = rootElement.getChildren("userMsg");
		String nextMsgId = Integer.toString(docElements.size());


		Element userMsg = new Element("userMsg");
		userMsg.setAttribute(new Attribute("id", nextMsgId));
		userMsg.addContent(new Element("statusFlag").setText("true"));
		userMsg.addContent(new Element("userName").setText(nameUser));
		userMsg.addContent(new Element("avatar").setText(userAvatar));
		userMsg.addContent(new Element("code").setText(statusCodeStr));
		//userMsg.addContent(new Element("status").setText(statusCode));
		userMsg.addContent(new Element("toUser").setText(""));
		userMsg.addContent(new Element("msg").setText(txtMsg));
		userMsg.addContent(new Element("date").setText(datef));
		userMsg.addContent(new Element("toUserName").setText(""));
		userMsg.addContent(new Element("avatarToUser").setText(""));


		rootElement.addContent(userMsg);

		XMLOutputter makeChatFile = new XMLOutputter();

		makeChatFile.setFormat(Format.getPrettyFormat());
		makeChatFile.output(doc, new FileWriter("/var/www/java/supportSys/WebContent/WEB-INF/xml-chat-logs/"+hashItem+".xml"));

		return true;
	}

	public boolean updateChatTransfer()
	{

		return false;

	}

}
