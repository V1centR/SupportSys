package com.supportsys.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthValidate extends HandlerInterceptorAdapter {

	/**
	 * Session validation
	 *
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller) throws Exception, IOException
	{

		String uri = request.getRequestURI();
		if(uri.endsWith("login") || uri.contains("resources") || uri.endsWith("authuser")){
			return true;
		}

		if(request.getSession().getAttribute("userHash") != null)
		{
			return true;
		}

		response.sendRedirect("/login");
		return false;

	}


}
