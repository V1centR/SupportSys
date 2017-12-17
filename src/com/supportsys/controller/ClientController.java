package com.supportsys.controller;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.supportsys.entity.Client;
import com.supportsys.repo.ClientRepo;

@Controller
public class ClientController {

	/**
	 * List clients by request
	 * @param model
	 * @return
	 */
	@RequestMapping("/clients/list")
	public ModelAndView clients(Model model)
	{
		return new ModelAndView("clientForm","","");
	}

	@RequestMapping(value="/clients/{keyWord}/{token}", method=RequestMethod.GET)
	public @ResponseBody String getClients(@PathVariable String keyWord, @PathVariable String token) throws JSONException
	{
		JSONObject container = new JSONObject();
		JSONObject items = new JSONObject();
		List<Client> clientList = null;

		if(keyWord.equals("null"))
		{
			clientList = new ClientRepo().getAllClients();
		} else {
			clientList = new ClientRepo().getClientsByKeyWord(keyWord);
		}

		System.out.println("Object returned:: " + clientList);

		for(Client clientItem: clientList)
		{
			items.put("clientId", clientItem.getId());
			items.put("clientName", clientItem.getName());
			items.put("clientDistrict", clientItem.getBairro());
			items.put("clientCity", clientItem.getCity());
			items.put("clientUf", clientItem.getUfBean().getSign());
			items.put("clientLevel", clientItem.getLevel());
			items.put("clientDescription", clientItem.getDesc());

			container.put(""+clientItem.getId()+"", items);
			items = new JSONObject();
		}

		String jsonContainer = container.toString();

		return jsonContainer;
	}


}
