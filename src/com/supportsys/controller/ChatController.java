package com.supportsys.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.jdom2.JDOMException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping(value="/chat/{idItem}/{hashItem}", method=RequestMethod.GET,produces={"application/xml", "application/json"})
	public @ResponseBody FileSystemResource loadChat(@PathVariable Integer idItem, @PathVariable String hashItem, HttpServletResponse response) throws JSONException, IOException, JDOMException
	{
		JSONObject jsonContainer = new JSONObject();
		JSONObject jsonItem = new JSONObject();

		System.out.println("idItem:: " + idItem + "\n hashItem:: " + hashItem);


		boolean validChat = new HelpRepo().checkIdAndHash(idItem, hashItem);

		if(validChat == true)
		{
			String chatEngaged = new ChatModel().initChat(hashItem);

			if(chatEngaged != null) {
				System.out.println("Chat Iniciado OK");

				response.setContentType("application/xml");
				//System.out.println("Arquivo OK::" + chatEngaged);
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
