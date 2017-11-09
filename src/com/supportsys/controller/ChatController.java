package com.supportsys.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom2.JDOMException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.supportsys.model.ChatModel;
import com.supportsys.repo.HelpRepo;

/**
 * XML chat based
 * for reduce database requests
 * @author Vicent R. vicentcdb@gmail.com
 *
 */
@Controller
public class ChatController {

	@RequestMapping(value="/chat/{idItem}/{hashItem}", method=RequestMethod.POST,produces={"application/xml", "application/json"})
	public @ResponseBody FileSystemResource loadChat(@RequestBody Object jsonMsg, @PathVariable Integer idItem, @PathVariable String hashItem, HttpServletResponse response, HttpServletRequest request) throws JSONException, IOException, JDOMException
	{
		System.out.println("exec ok 1");

		JSONObject jsonContainer = new JSONObject();
		JSONObject jsonItem = new JSONObject();

		String jsonData = jsonMsg.toString();

		System.out.println("Mensagem Pronta:: " + jsonData);


		JSONObject jsonMsgUser = new JSONObject(jsonData);

		String txtMsg = jsonMsgUser.getString("msgTxt").toString();
		String mode = jsonMsgUser.getString("setMode").toString();


		System.out.println("MODE:::" + mode);


		System.out.println("idItem:: " + idItem + "\n hashItem:: " + hashItem);




		boolean validChat = new HelpRepo().checkIdAndHash(idItem, hashItem);

		if(validChat == true)
		{
			Object idUser = request.getSession().getAttribute("idUser");
			String nameUser = request.getSession().getAttribute("userName").toString();
			String userSname =  request.getSession().getAttribute("userSname").toString();
			String userAvatar =  request.getSession().getAttribute("userAvatar").toString();
			String userName = nameUser +" "+ userSname;

			System.out.println("Avatar user::" + userAvatar);

			String chatEngaged = new ChatModel().initChat(hashItem,idUser,userName,userAvatar,txtMsg,mode);

			if(chatEngaged != null) {
				System.out.println("Chat Iniciado OK");

				response.setContentType("application/xml");
				return new FileSystemResource(chatEngaged);

			}else {
				//return null;
			}

		} else {
			//return Response.SC_UNAUTHORIZED;
			//return null;
		}
		return null;

	}

}
