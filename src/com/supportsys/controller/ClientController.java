package com.supportsys.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.supportsys.entity.Client;
import com.supportsys.entity.Uf;
import com.supportsys.interfaces.Operactions;
import com.supportsys.model.ClientModel;
import com.supportsys.repo.ClientRepo;

@Controller
public class ClientController implements Operactions {


	/**
	 *
	 */
	@RequestMapping(value="/client/add", method=RequestMethod.POST)
	@Override
	public @ResponseBody Integer insertItem(@RequestBody Object jsonStr) throws JSONException
	{
		String jsonFormData = jsonStr.toString();
		JSONObject jsonItems = new JSONObject(jsonFormData);

		/*TODO field "active" added, update
		logical code to validate user active and non active in your system */
		boolean addClientOk = new ClientModel().addClient(jsonItems);

		if(addClientOk == true)
		{
			return Response.SC_CREATED;
		} else {
			return Response.SC_INTERNAL_SERVER_ERROR;
		}
	}

	/**
	 *
	 */
	@RequestMapping(value="/client/edit", method=RequestMethod.POST)
	@Override
	public @ResponseBody Integer updateItem(@RequestBody Object jsonStr) throws JSONException
	{
		String jsonFormData = jsonStr.toString();
		JSONObject jsonItems = new JSONObject(jsonFormData);

		/*TODO field "active" added, update
		logical code to validate user active and non active in your system */
		boolean addClientOk = new ClientModel().updateClient(jsonItems);

		if(addClientOk == true)
		{
			return Response.SC_CREATED;
		} else {
			return Response.SC_INTERNAL_SERVER_ERROR;
		}
	}

	@Override
	public Integer deleteItem(Object jsonStr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView updateItemView(Model model, Integer idUser) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * List clients by request
	 * @param model
	 * @return
	 */
	@Override
	@RequestMapping("/clients/list")
	public ModelAndView listItemsView(Model model)
	{
		return new ModelAndView("clientList","","");
	}

	/**
	 * List clients by request
	 * @param model
	 * @return
	 */
	@Override
	@RequestMapping("/clients/add")
	public ModelAndView insertItemView(Model model)
	{

		model.addAttribute("mode", "add");

		return new ModelAndView("clientForm","","");
	}

	/**
	 * List clients by request
	 * @param model
	 * @return
	 */
	@RequestMapping("/clients/edit/{idClient}/{token}")
	public ModelAndView clientsEditView(Model model, @PathVariable Integer idClient, @PathVariable String token)
	{
		Client clientInfo = new ClientRepo().getClientInfo(idClient);

		String logoImg = clientInfo.getImage().getId() +"."+clientInfo.getImage().getExt();
		List<Uf> statesList = new ClientModel().getStates();

		model.addAttribute("mode", "edit");
		model.addAttribute("clientInfo", clientInfo);
		model.addAttribute("statesList", statesList);

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
