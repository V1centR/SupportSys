package com.supportsys.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.supportsys.entity.Client;
import com.supportsys.entity.Department;
import com.supportsys.entity.User;
import com.supportsys.entity.UserGroup;
import com.supportsys.model.UserModel;
import com.supportsys.repo.UserRepo;

@Controller
public class UserController {


	@RequestMapping(value="/users/exec", method=RequestMethod.POST)
	public @ResponseBody int execUserAdd(@RequestBody Object jsonStr, HttpServletRequest resquest) throws JSONException
	{
		String jsonFormData = jsonStr.toString();
		JSONObject jsonItems = new JSONObject(jsonFormData);

		/*TODO field "active" added, update
		logical code to validate user active and non active in your system */
		boolean addUserOk = new UserModel().addUser(jsonItems);

		if(addUserOk == true)
		{
			return Response.SC_CREATED;
		} else {
			return Response.SC_INTERNAL_SERVER_ERROR;
		}

	}

	/**
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping("/users/all")
	public ModelAndView listUsersAction(Model model)
	{
		List<User> allUsers = new UserModel().getAllUsers();
		List<Client> allClients = new UserModel().getClients();

		model.addAttribute("listUsers", allUsers);
		model.addAttribute("listClients", allClients);

		return new ModelAndView("usersList","", "");
	}

	@RequestMapping(value="/users/byClient/{clientId}", method=RequestMethod.POST)
	public @ResponseBody String userByClient(@RequestBody Object jsonStr, HttpServletRequest request) throws JSONException, SQLException
	{
		String jsonStrConvert = jsonStr.toString();
		JSONObject jsonItem = new JSONObject(jsonStrConvert);

		JSONObject jsonContainer = new JSONObject();
		JSONObject jsonUserData = new JSONObject();

		Integer idClient = Integer.parseInt(jsonItem.get("idClient").toString());

		List<Object[]> usersByClient = new UserRepo().getUsersByClient(idClient);

		if(!usersByClient.isEmpty())
		{
			for(Object[] userData : usersByClient)
			{

				String nomeUser = userData[1] +" "+ userData[2];

				jsonUserData.put("idUser", userData[0]);
				jsonUserData.put("nameUser", nomeUser);
				jsonUserData.put("avatar", userData[6]);
				jsonUserData.put("deptUser", userData[8]);
				jsonUserData.put("groupUser", userData[9]);
				jsonUserData.put("clientName", userData[11]);

				jsonContainer.put("" + userData[0] + "", jsonUserData);
				jsonUserData = new JSONObject();
			}

			System.out.println("Data User:: " + jsonContainer.toString());
			return jsonContainer.toString();

		} else {

			return "empty";
		}

	}

	/**
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping("/users/edit/{idUser}")
	public ModelAndView editUserAction(Model model, @PathVariable Integer idUser )
	{
		List<Client> fullClients = new UserModel().getClients();
		List<UserGroup> groupList = new UserModel().getGroups();
		User userInfo = new UserRepo().getUserInfo(idUser);

		model.addAttribute("clientList", fullClients);
		model.addAttribute("groupList", groupList);
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("mode", "edit");

		return new ModelAndView("usersForm","", "");
	}

	/**
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping("/users/new")
	public ModelAndView addUserAction(Model model)
	{
		List<Client> fullClients = new UserModel().getClients();
		List<UserGroup> groupList = new UserModel().getGroups();

		model.addAttribute("clientList", fullClients);
		model.addAttribute("groupList", groupList);
		model.addAttribute("mode", "add");

		return new ModelAndView("usersForm","", "");
	}

	/**
	 * Return JSON departments by client ID
	 * @param clientId
	 * @return
	 * @throws JSONException
	 */
	@RequestMapping(value="/getdepartmentlist/{clientId}", method=RequestMethod.GET)
	public @ResponseBody String getItems(@PathVariable String clientId) throws JSONException
	{

		JSONObject jsonContainer = new JSONObject();
		JSONObject jsonItems = new JSONObject();

		List<Department> deptList = new UserModel().getDepartments(Integer.parseInt(clientId));

		for(Department clientItems: deptList)
		{
			jsonItems.put("id", clientItems.getId());
			jsonItems.put("name", clientItems.getName());

			jsonContainer.put("" + clientItems.getId() +"", jsonItems);
			jsonItems = new JSONObject();
		}

		return jsonContainer.toString();
	}
}
