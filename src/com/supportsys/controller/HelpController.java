package com.supportsys.controller;


import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import com.supportsys.entity.Status;
import com.supportsys.entity.SupportUser;
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
	
	@RequestMapping("/chamados/update")
	public @ResponseBody int updateItem(@RequestBody Object jsonStr) throws JSONException
	{
		String jsonFormData = jsonStr.toString();
		JSONObject jsonItems = new JSONObject(jsonFormData);
		
		boolean updateItemOk = new HelpModel().updateItem(jsonItems);
		
		if(updateItemOk == true) {
			return Response.SC_CREATED;			
		}else {
			return Response.SC_INTERNAL_SERVER_ERROR;
		}
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
		HttpSession sessId = request.getSession();
		
		//Send to Model
		boolean createHelp = new HelpModel().createHelp(jsonItems, userHelp,sessId.getId());
		
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
		List<Help> dataItem = new HelpModel().openHelp(idHelp);
		List<Status> listStatus = new HelpModel().getStatus();
		List<SupportUser> listSupportUsers = new HelpModel().getSupportUsers();
		
		//get single data in list
		//System.out.println(dataItem.get(0).getHashSecure());
		
		model.addAttribute("idItem", dataItem.get(0).getId());
		model.addAttribute("hashItem", dataItem.get(0).getHashSecure());
		model.addAttribute("listSupportUsers", listSupportUsers);
		model.addAttribute("statusList", listStatus);
		model.addAttribute("dataItem", dataItem);
		
		return new ModelAndView("open");
		
	}
	
}
