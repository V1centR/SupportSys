package com.supportsys.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.*;
import javax.persistence.Persistence;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supportsys.entity.Help;
import com.supportsys.entity.User;


@WebServlet("/HelpController")
public class HelpController extends HttpServlet{
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		Connection db = null;
		PrintWriter out = response.getWriter();		
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("supportSys");
		EntityManager em = emf.createEntityManager();
		
		User usuario = em.find(User.class, 1);
		out.println("<b>User:</b> " + usuario.getName());
		
		
		
		
		String formMode = request.getParameter("formMode");
		String idHelp = request.getParameter("idHelp");
		String helpLabel = request.getParameter("helpLabel");
		String cat = request.getParameter("cat");
		String setor_name = request.getParameter("setor_name");
		String phone = request.getParameter("phone");
		String helpTxt = request.getParameter("desc");
		Calendar dataHelp = null;
		
		
		try {
			//YYYY-MM-DD HH:MM:SS
			
			SimpleDateFormat date = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
			
			dataHelp = Calendar.getInstance();
			
			User user = new User();
			user.getName();
			
			Help help = new Help();
			
			/*
			 * helpLabel
				cat
				setor_name
				phone
				desc
			 * 
			 * */
			
			help.setHelpLabel(helpLabel);
			help.setHelpTxt(helpTxt);
//			help.setUser(1);
//			help.setSupportUser(supportUser);
//			help.setStatusBean(1);
//			help.setDateHelp(dateHelp);
//			help.setTypeHelpBean(typeHelpBean);
//			help.setTags(tags);
			
			
			
			
			//dataHelp.setTime(date);
					
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	
	public boolean execHelp(String[] dataForm) {
		
		boolean execOk = true;
		
		
		
		
		return execOk;
		
	}

}
