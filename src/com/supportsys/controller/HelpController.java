package com.supportsys.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.supportsys.entity.Department;
import com.supportsys.entity.Help;
import com.supportsys.entity.TypeHelp;
import com.supportsys.model.HelpModel;


@Controller
public class HelpController {
	
	@RequestMapping("/chamados/novo-chamado")
	public ModelAndView execHelp(Model model, HttpServletRequest request)
	{	
		HttpSession session = request.getSession();
		String clientId_ =  session.getAttribute("userClient").toString();
		int clientId = Integer.parseInt(clientId_);
		
		List<Department> deptList = new HelpModel().getDepartment(clientId);
		List<TypeHelp> listTypes = new HelpModel().getTypes();
		
		model.addAttribute("listDept", deptList);
		model.addAttribute("listTypes", listTypes);
		
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
	public @ResponseBody int addHelp(@RequestBody Object jsonStr, HttpServletRequest request) throws JSONException, IOException	{
		//edresxe
		
		//Funcionando! formato recebido "{\"nome\":\"ASSPM\",\"email\":\"imprensa@asspm.org.br\"}";
		String jsonFormData = jsonStr.toString();
		JSONObject jsonItems = new JSONObject(jsonFormData);
		//aqui virá uma sessão
		Integer userHelp = 8;
		
		//Send to Model
		boolean createHelp = new HelpModel().createHelp(jsonItems, userHelp);
		
		if(createHelp == true) {
			return Response.SC_CREATED;			
		}else {
			return Response.SC_INTERNAL_SERVER_ERROR;
		}
	}
	
	@RequestMapping("/chamados/list")
	public ModelAndView listHelp(Model model)
	{
		//attribute - value
		//model.addAttribute("attribute", value);
		List<Help> helpList = new HelpModel().list();
		
		model.addAttribute("dataHelp", helpList);
		
		return new ModelAndView("list");
	}
	
	@RequestMapping("/chamados/open/{idHelp}")
	public ModelAndView openItem(Model model, @PathVariable Integer idHelp)
	{
		System.out.println("ID do chamado:: " + idHelp);
		
		
		return new ModelAndView("open");
		
	}
}
