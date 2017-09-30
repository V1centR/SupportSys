package com.supportsys.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.JsonViewRequestBodyAdvice;

import com.supportsys.entity.Client;
import com.supportsys.entity.Department;
import com.supportsys.entity.Help;
import com.supportsys.model.HelpModel;


@Controller
public class HelpController {
	
	@RequestMapping("/new-help")
	public ModelAndView execHelp()
	{		
		return new ModelAndView("formhelp","", "");
	}
	
	@RequestMapping(value="addhelp", method=RequestMethod.POST)
	//@ResponseStatus(HttpStatus.OK)
	public @ResponseBody void addHelp(@RequestBody Object jsonStr) throws JSONException{
		//edresxe
	
		
		//Funcionando! formato recebido "{\"nome\":\"ASSPM\",\"email\":\"imprensa@asspm.org.br\"}";
		String jsonFormData = jsonStr.toString();
		JSONObject jsonItems = new JSONObject(jsonFormData);
		//aqui virá uma sessão
		Integer userHelp = 1;
		
		//Send to Model
		boolean createHelp = new HelpModel().createHelp(jsonItems, userHelp);
		
		System.out.println(createHelp);
		
	}
	
	
	@RequestMapping(value="/addhelpOLD", method = RequestMethod.POST, produces="application/json")
	public String addHelpOLD(@ModelAttribute("addHelp") Department departamento, BindingResult result, ModelMap model, HttpServletResponse response) throws IOException 
	{		
		//String addConfirm = "{'status':true}";
		Connection db = null;
		
		//string support especificado no persistence.xml
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("support");
		EntityManager em = emf.createEntityManager();
		
		Client cliente = em.find(Client.class, 1);
		
		System.out.println(cliente);
		//System.out.println(cliente);
		
		PrintWriter out = response.getWriter();
		
		//model.addAttribute("helpLabel", departamento.getName());
		
		out.println("[{',label' : 'jsonOK'}]");
		
		
		//show json result in generic.jsp
		return "generic";
		
	}

}
