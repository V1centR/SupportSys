package com.supportsys.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {


	@RequestMapping("/users/new")
	public ModelAndView addUserAction()
	{



		return new ModelAndView("usersAdd","", "");
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
