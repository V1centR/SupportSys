package com.supportsys.controller;

import java.io.IOException;

import org.jdom2.JDOMException;
import org.json.JSONException;
import org.json.JSONObject;
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

	@RequestMapping(value="/chat/{idItem}/{hashItem}", method=RequestMethod.GET)
	public @ResponseBody String loadChat(@PathVariable Integer idItem, @PathVariable String hashItem) throws JSONException, IOException, JDOMException
	{
		JSONObject jsonContainer = new JSONObject();
		JSONObject jsonItem = new JSONObject();

		System.out.println("idItem:: " + idItem + "\n hashItem:: " + hashItem);


		boolean validChat = new HelpRepo().checkIdAndHash(idItem, hashItem);

		if(validChat == true)
		{
			Boolean chatEngaged = new ChatModel().initChat(hashItem);

			if(chatEngaged == true) {
				System.out.println("Chat Iniciado OK");
			}
		}

		String testOK = "TEST-OK";

		return testOK;
	}

}
