package com.supportsys.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
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

import com.supportsys.entity.User;
import com.supportsys.model.AuthModel;

@Controller
public class AuthController {

	@RequestMapping("/login")
	public ModelAndView loginHome()
	{
		return new ModelAndView("login","","");
	}

	@RequestMapping(value="authuser", method=RequestMethod.POST)
	public @ResponseBody int authenticate(@RequestBody Object jsonAuth, HttpServletRequest request) throws JSONException, IOException
	{
		String jsonData = jsonAuth.toString();
		JSONObject loginData = new JSONObject(jsonData);

		User userData = new AuthModel().authUser(loginData);

		if(userData != null) {

			HttpSession userSession = request.getSession();
			String avatar = userData.getImage().getId()+"." + userData.getImage().getExt();

			userSession.setAttribute("idUser", userData.getId());
			userSession.setAttribute("userName", userData.getName());
			userSession.setAttribute("userSname", userData.getSname());
			userSession.setAttribute("userAvatar", avatar);
			userSession.setAttribute("userEmail", userData.getEmail());
			userSession.setAttribute("userClient", userData.getDepartment().getClientBean().getId());
			userSession.setAttribute("userHash", userSession.getId());

			return Response.SC_OK; //200

		}else {
			return Response.SC_UNAUTHORIZED; //401
		}
	}

	@RequestMapping(value="logout", method=RequestMethod.POST)
	public @ResponseBody int logOut(@RequestBody Object jsonLogOut, HttpServletRequest request) throws JSONException
	{
		String jsonData = jsonLogOut.toString();
		JSONObject logOut = new JSONObject(jsonData);
		//Object userEmail = userData.get("email").toString();
		Object hashUser = logOut.get("infoLogout").toString();

		HttpSession sessionCheck = request.getSession();

		System.out.println("Hash eviado via json:: " + hashUser);
		System.out.println("Session aberta:: " + sessionCheck.getId());

		try {
			HttpSession userSession = request.getSession();
			userSession.invalidate();

			return Response.SC_OK; //200
		} catch (Exception e) {
			// TODO: handle exception
			return Response.SC_BAD_GATEWAY; //502
		}
	}
}
