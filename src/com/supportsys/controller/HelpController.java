package com.supportsys.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/spring")
public class HelpController {
	
	@RequestMapping("/test")
	public ModelAndView execHelp()
	{
		String execOk = "Vamos ganhar 6000 com Java e Spring";
		
		return new ModelAndView(
				
				"formhelp","message", execOk
				
				);
		
	}

}
