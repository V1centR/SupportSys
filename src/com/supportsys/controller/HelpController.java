package com.supportsys.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supportsys.entity.Help;
import com.supportsys.model.ConnectionFactory;


@WebServlet("/HelpController")
public class HelpController extends HttpServlet{
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		Connection db = null;
		
		/*
		 * 
		formMode
		idHelp
		helpLabel
		cat
		setor_name
		phone
		desc
		 * 
		 * 
		 * */
		
		String formMode = request.getParameter("formMode");
		String idHelp = request.getParameter("idHelp");
		String helpLabel = request.getParameter("helpLabel");
		String cat = request.getParameter("cat");
		String setor_name = request.getParameter("setor_name");
		String phone = request.getParameter("phone");
		String helpTxt = request.getParameter("desc");
		Calendar dataHelp = null;
		
		PrintWriter out = response.getWriter();		
		//out.println("<b>Classe Java respondendo! AAA</b> " + helpLabel);
		
		db = new ConnectionFactory().getConnection();
		out.println("<b>Conexão aberta OK </b> " + helpLabel);
		
		
		try {
			//YYYY-MM-DD HH:MM:SS
			
			
			
			SimpleDateFormat date = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
			
			dataHelp = Calendar.getInstance();
			
		//	Help help = new Help();
			
			/*
			 * helpLabel
				cat
				setor_name
				phone
				desc
			 * 
			 * */
			
//			help.setHelpLabel(helpLabel);
//			help.setHelpTxt(helpTxt);
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
