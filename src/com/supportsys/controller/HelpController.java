package com.supportsys.controller;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Response;
import org.jdom2.JDOMException;
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
import com.supportsys.entity.TypeHelp;
import com.supportsys.entity.User;
import com.supportsys.model.ChatModel;
import com.supportsys.model.HelpModel;
import com.supportsys.repo.HelpRepo;


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
	public @ResponseBody int updateItem(@RequestBody Object jsonStr, HttpServletRequest request) throws JSONException, JDOMException, IOException
	{
		String jsonFormData = jsonStr.toString();
		JSONObject jsonItems = new JSONObject(jsonFormData);

		String txtMsg;

		Integer itemId = Integer.parseInt(jsonItems.get("idItem").toString());
		String hashThisItem = jsonItems.get("hashItem").toString();

		Object idUser = request.getSession().getAttribute("idUser");
		String nameUser = request.getSession().getAttribute("userName").toString();
		String userSname =  request.getSession().getAttribute("userSname").toString();
		String userAvatar =  request.getSession().getAttribute("userAvatar").toString();
		String userName = nameUser +" "+ userSname;

		Integer supportUserAdded = Integer.parseInt(jsonItems.get("supportUser").toString());
		Integer selectedStatus = Integer.parseInt(jsonItems.get("statusHelp").toString());
		String msgTemplate = "Alterou este chamado para ";
		String changeStatus = "";

		Help thisItem = new HelpRepo().getItem(itemId, hashThisItem);
		Integer statusSet = thisItem.getStatusBean().getId();

		switch (selectedStatus) {

			case 1:
				 changeStatus = "<b>Aberto</b>";
				break;
			case 2:
				changeStatus = "<b>Executando</b>";
				break;
			case 3:
				changeStatus = "<b>Concluído</b>";
				break;
			case 4:
				 changeStatus = "<b>Cancelado</b>";
				break;
		}

		txtMsg = msgTemplate + changeStatus;

		try {

			new ChatModel().updateChatStatus(itemId,hashThisItem,idUser,userName,userAvatar,txtMsg,selectedStatus,supportUserAdded);
			new HelpModel().updateItem(jsonItems);

			return Response.SC_CREATED;

		} catch (Exception e) {
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
	public @ResponseBody int addHelp(@RequestBody Object jsonStr, HttpServletRequest request) throws JSONException, IOException
	{

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

		JSONObject jsonContainer = new JSONObject();
		JSONObject jsonItems = new JSONObject();
		JSONObject supportUserAdded = new JSONObject();

		List<Help> helpList = new HelpModel().list(status);
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy H:mm");

		for(Help dataItems: helpList) {

			String dateF = df.format(dataItems.getDateHelp());

			if(dataItems.getSupportUser() == null)
			{
				supportUserAdded = null;

			} else {

				String supportUserName = dataItems.getSupportUser().getName() +" " + dataItems.getSupportUser().getSname();
				String supportUserAvatar = dataItems.getSupportUser().getImage().getId() +"." + dataItems.getSupportUser().getImage().getExt();
				String supportUserDept = dataItems.getSupportUser().getDepartment().getName();

				supportUserAdded.put("id", dataItems.getSupportUser().getId());
				supportUserAdded.put("name", supportUserName);
				supportUserAdded.put("supportUserAvatar", supportUserAvatar);
				supportUserAdded.put("supportDept", supportUserDept);
			}

			jsonItems.put("id", dataItems.getId());
			jsonItems.put("helpLabel", dataItems.getHelpLabel());
			jsonItems.put("helpTxt", dataItems.getHelpTxt());
			jsonItems.put("userId", dataItems.getUser().getId());
			jsonItems.put("userIdName", dataItems.getUser().getName() + " " + dataItems.getUser().getSname());
			jsonItems.put("userAvatar", dataItems.getUser().getImage().getId() +"." + dataItems.getUser().getImage().getExt());
			jsonItems.put("statusId", dataItems.getStatusBean().getId());
			jsonItems.put("statusName", dataItems.getStatusBean().getName());
			jsonItems.put("departmentId", dataItems.getDepartment().getId());
			jsonItems.put("departmentName", dataItems.getDepartment().getName());
			jsonItems.put("typeHelpId", dataItems.getTypeHelpBean().getId());
			jsonItems.put("typeHelpName", dataItems.getTypeHelpBean().getName());
			jsonItems.put("hashSecure", dataItems.getHashSecure());
			jsonItems.put("supportUser", supportUserAdded);
			jsonItems.put("dateHelp", dateF);

			jsonContainer.put("" + dataItems.getId() + "", jsonItems);
			jsonItems = new JSONObject();
			supportUserAdded = new JSONObject();
		}

		return jsonContainer.toString();
	}

	@RequestMapping("/chamados/list/{status}")
	public ModelAndView listHelp(Model model, @PathVariable String status)
	{

		List<Help> helpList = new HelpModel().list(status);
		model.addAttribute("dataHelp", helpList);

		return new ModelAndView("listHelp-api");
	}

	@RequestMapping("/help/open/{idHelp}/{hashItem}")
	public ModelAndView openItem(Model model, @PathVariable Integer idHelp, @PathVariable String hashItem, HttpServletRequest request)
	{
		Help dataItem = new HelpModel().openHelp(idHelp,hashItem);
		List<Status> listStatus = new HelpModel().getStatus();
		List<User> listSupportUsers = new HelpRepo().getSupportUsers();

		try {
			Integer checkSupportAdded = dataItem.getSupportUser().getId();
			model.addAttribute("supportUserAdded", checkSupportAdded);
		} catch (Exception e) {
			Integer setNone = 0;
			model.addAttribute("supportUserAdded", setNone);
		}

		HttpSession userSession = request.getSession();
		String userAvatar =  (String) userSession.getAttribute("userAvatar");

		model.addAttribute("idItem", dataItem.getId());
		model.addAttribute("userAvatar", userAvatar);
		model.addAttribute("hashItem", dataItem.getHashSecure());
		model.addAttribute("listSupportUsers", listSupportUsers);
		model.addAttribute("statusList", listStatus);
		model.addAttribute("dataItem", dataItem);
		model.addAttribute("typeAdded", dataItem.getTypeHelpBean());
		model.addAttribute("statusAdded", dataItem.getStatusBean().getId());
		model.addAttribute("webChat", dataItem.getStatusBean().getId());

		return new ModelAndView("open");

	}

}
