package com.supportsys.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public @ResponseBody String loadChat(@PathVariable Integer idItem, @PathVariable String hashItem) throws JSONException
	{
		JSONObject jsonContainer = new JSONObject();
		JSONObject jsonItem = new JSONObject();

		System.out.println("idItem:: " + idItem + "\n hashItem:: " + hashItem);

		boolean validChat = new HelpRepo().checkIdAndHash(idItem, hashItem);

		if(validChat == true)
		{
			//List<ChatHelp> fullChat = new ChatModel().getChat(idItem);
			//DateFormat df = new SimpleDateFormat("dd/MM/yyyy H:mm");




			/*

			for(ChatHelp chatItem: fullChat)
			{
				//String dateF = df.format(dataItems.getDateHelp());
				jsonItem.put("nameUser", chatItem.getIdUserPost()); //campo não relacionado
				jsonItem.put("idUser", chatItem.getIdUserPost()); //campo não relacionado
				jsonItem.put("dateMsg", chatItem.getDate()); //campo não relacionado
				jsonItem.put("msg", chatItem.getTxt()); //campo não relacionado

				//campos não relacionados refazer entidade

				jsonContainer.put(""+ chatItem.getIdUserPost() +"", jsonItem);


			} */


		}



		String testOK = "TEST-OK";

		return testOK;
	}

}
