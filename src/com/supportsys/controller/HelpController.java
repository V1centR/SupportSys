package com.supportsys.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.supportsys.entity.Client;
import com.supportsys.entity.Department;


@Controller
public class HelpController {
	
	@RequestMapping("/new-help")
	public ModelAndView execHelp()
	{		
		return new ModelAndView("formhelp","", "");
		
	}
	
	@RequestMapping(value="/teste", method = RequestMethod.GET, produces = "application/json")
	//@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String getClient(@RequestParam(value="jsonStr") String jsonStr, HttpServletRequest request ) throws JSONException{
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("support");
		EntityManager em = emf.createEntityManager();
		
		Client cliente = em.find(Client.class, 1);
		cliente.getName();
		cliente.getEmail();
		
		JSONObject dataItem = new JSONObject();
		
		dataItem.put("nome", cliente.getName());
		dataItem.put("email", cliente.getEmail());
		
		String jsonString = dataItem.toString();
		System.out.println(jsonStr);
		

		return jsonString;
	}
	
	
	@RequestMapping(value="/addhelp", method = RequestMethod.POST, produces="application/json")
	public String addHelp(@ModelAttribute("addHelp") Department departamento, BindingResult result, ModelMap model, HttpServletResponse response) throws IOException 
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
