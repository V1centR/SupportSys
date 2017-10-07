package com.supportsys.controller;


import org.apache.catalina.connector.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.supportsys.model.HelpModel;


@Controller
public class HelpController {
	
	@RequestMapping("/chamados/novo-chamado")
	public ModelAndView execHelp()
	{		
		return new ModelAndView("formhelp","", "");
	}
	
	@RequestMapping("/home")
	public ModelAndView homeAction()
	{		
		return new ModelAndView("index","", "");
	}
	
	/**
	 * Method addHelp
	 * Add new data from help user 
	 */
	@RequestMapping(value="addhelp", method=RequestMethod.POST)
	//@ResponseStatus(HttpStatus.OK)
	public @ResponseBody int addHelp(@RequestBody Object jsonStr) throws JSONException{
		//edresxe
		
		//Funcionando! formato recebido "{\"nome\":\"ASSPM\",\"email\":\"imprensa@asspm.org.br\"}";
		String jsonFormData = jsonStr.toString();
		JSONObject jsonItems = new JSONObject(jsonFormData);
		//aqui virá uma sessão
		Integer userHelp = 1;
		
		//Send to Model
		boolean createHelp = new HelpModel().createHelp(jsonItems, userHelp);
		
		if(createHelp == true) {
			return Response.SC_CREATED;			
		}else {
			return Response.SC_INTERNAL_SERVER_ERROR;
		}
	}
}
