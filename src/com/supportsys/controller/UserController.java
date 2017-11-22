package com.supportsys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.supportsys.entity.Department;
import com.supportsys.model.UserModel;

@Controller
public class UserController {


	@RequestMapping("/users/new")
	public ModelAndView addUserAction(Model model)
	{
		List<Client> fullClients = new UserModel().getClients();
		model.addAttribute("clientList", fullClients);

		return new ModelAndView("usersAdd","", "");
	}

	/**
	 * Return JSON departments by client ID
	 * @param clientId
	 * @return
	 * @throws JSONException
	 */
	@RequestMapping(value="/getdepartmentlist/{clientId}", method=RequestMethod.GET)
	public @ResponseBody String getItems(@PathVariable String clientId) throws JSONException
	{

		JSONObject jsonContainer = new JSONObject();
		JSONObject jsonItems = new JSONObject();

		List<Department> deptList = new UserModel().getDepartments(Integer.parseInt(clientId));

		for(Department clientItems: deptList)
		{
			jsonItems.put("id", clientItems.getId());
			jsonItems.put("name", clientItems.getName());

			jsonContainer.put("" + clientItems.getId() +"", jsonItems);
			jsonItems = new JSONObject();
		}

		return jsonContainer.toString();
	}


	@RequestMapping(value="/users/exec", method=RequestMethod.POST)
	public @ResponseBody int userLogged(@RequestBody Object jsonStr, HttpServletRequest resquest) throws JSONException
	{
		String jsonFormData = jsonStr.toString();
		JSONObject jsonItems = new JSONObject(jsonFormData);

		System.out.println("Json itens received:: " + jsonItems);

		return Response.SC_CREATED;
	}

}
