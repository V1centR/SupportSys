package com.supportsys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController {

	@RequestMapping("/login")
	public ModelAndView loginHome()
	{
		return new ModelAndView("login","","");
	}
	
}
