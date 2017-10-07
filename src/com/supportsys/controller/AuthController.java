package com.supportsys.controller;

import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.supportsys.model.AuthModel;

@Controller
public class AuthController {

	@RequestMapping("/login")
	public ModelAndView loginHome()
	{
		return new ModelAndView("login","","");
	}
	
	@RequestMapping(value="authuser", method=RequestMethod.POST)
	public @ResponseBody int authenticate(@RequestBody Object jsonAuth, HttpSession session) throws JSONException 
	{
		String jsonData = jsonAuth.toString();
		JSONObject loginData = new JSONObject(jsonData);
		
		boolean authUser = new AuthModel().authUser(loginData);
		
		if(authUser == true) {
			return Response.SC_OK; //200
		}else {
			return Response.SC_UNAUTHORIZED; //401
		}
	}
}
