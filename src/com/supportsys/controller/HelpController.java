package com.supportsys.controller;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

	@RequestMapping("/help/update")
	public @ResponseBody int updateItem(@RequestBody Object jsonStr) throws JSONException
	{
		String jsonFormData = jsonStr.toString();
		JSONObject jsonItems = new JSONObject(jsonFormData);

		String hashThisItem = jsonItems.get("hashItem").toString();

		System.out.println(hashThisItem);

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

	@RequestMapping(value="/gethelplist/{status}", method=RequestMethod.GET)
	public @ResponseBody String getItems(@PathVariable String status) throws JSONException {

		//String jsonDataStr = "{\"nome\":\"ASSPM\",\"email\":\"imprensa@asspm.org.br\"}";

		JSONObject jsonContainer = new JSONObject();
		JSONObject jsonItems = new JSONObject();

		List<Help> helpList = new HelpModel().list(status);
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy H:mm");

		for(Help dataItems: helpList) {

			String dateF = df.format(dataItems.getDateHelp());

			jsonItems.put("id", dataItems.getId());
			jsonItems.put("helpLabel", dataItems.getHelpLabel());
			jsonItems.put("helpTxt", dataItems.getHelpTxt());
			jsonItems.put("userId", dataItems.getUser().getId());
			jsonItems.put("userIdName", dataItems.getUser().getName() + " " + dataItems.getUser().getSname());
			jsonItems.put("statusId", dataItems.getStatusBean().getId());
			jsonItems.put("statusName", dataItems.getStatusBean().getName());
			jsonItems.put("departmentId", dataItems.getDepartment().getId());
			jsonItems.put("departmentName", dataItems.getDepartment().getName());
			jsonItems.put("typeHelpId", dataItems.getTypeHelpBean().getId());
			jsonItems.put("typeHelpName", dataItems.getTypeHelpBean().getName());
			jsonItems.put("hashSecure", dataItems.getHashSecure());
			jsonItems.put("dateHelp", dateF);

			jsonContainer.put("" + dataItems.getId() + "", jsonItems);
			jsonItems = new JSONObject();
		}

		return jsonContainer.toString();
	}

	@RequestMapping("/chamados/list/{status}")
	public ModelAndView listHelp(Model model, @PathVariable String status)
	{
		System.out.println("Selecionar apenas::" + status);

		List<Help> helpList = new HelpModel().list(status);
		model.addAttribute("dataHelp", helpList);

		//return new ModelAndView("list");
		return new ModelAndView("listHelp-api");
	}

	@RequestMapping("/chamados/open/{idHelp}/{hashItem}")
	public ModelAndView openItem(Model model, @PathVariable Integer idHelp, @PathVariable String hashItem)
	{
		List<Help> dataItem = new HelpModel().openHelp(idHelp,hashItem);
		List<Status> listStatus = new HelpModel().getStatus();
		List<SupportUser> listSupportUsers = new HelpModel().getSupportUsers();

		model.addAttribute("idItem", dataItem.get(0).getId());
		model.addAttribute("hashItem", dataItem.get(0).getHashSecure());
		model.addAttribute("listSupportUsers", listSupportUsers);
		model.addAttribute("statusList", listStatus);
		model.addAttribute("dataItem", dataItem);

		return new ModelAndView("open");

	}

}
