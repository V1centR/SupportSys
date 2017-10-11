package com.supportsys.controller;

import java.io.IOException;
import java.util.List;

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
		
		List<User> authUser = new AuthModel().authUser(loginData);
		
		System.out.println("Retorno dentro do controller:: " + authUser);
		
		if(authUser != null) {
			
			for(User itemData: authUser)
			{
				
				HttpSession userSession = request.getSession();
				String avatar = itemData.getImage().getId()+"-"+ itemData.getImage().getImgName() + "." + itemData.getImage().getExt();
				
				userSession.setAttribute("userName", itemData.getName());
				userSession.setAttribute("userSname", itemData.getSname());
				userSession.setAttribute("userAvatar", avatar);
				userSession.setAttribute("userEmail", itemData.getEmail());
				userSession.setAttribute("userClient", itemData.getClient());
				userSession.setAttribute("userHash", userSession.getId());
				
				//System.out.println(userSession.getAttribute("userAvatar"));
			}
			
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
	
	public boolean checkSession(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		
		if(session.getId() != null) {			
			return true;
		} else {			
			return false;
		}		
	}
}
