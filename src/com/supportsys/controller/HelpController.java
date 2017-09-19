package com.supportsys.controller;

import java.io.IOException;
import java.io.PrintWriter;
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


@WebServlet("/HelpController")
public class HelpController extends HttpServlet{
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		
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
		String labelHelp = request.getParameter("helpLabel");
		String cat = request.getParameter("cat");
		String setor_name = request.getParameter("setor_name");
		String phone = request.getParameter("phone");
		String desc = request.getParameter("desc");
		Calendar dataHelp = null;
		
		PrintWriter out = response.getWriter();		
		out.println("<b>Classe Java respondendo! AAA</b> " + labelHelp);
		
		
		try {
			//YYYY-MM-DD HH:MM:SS
			
			SimpleDateFormat date = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
			
			dataHelp = Calendar.getInstance();
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
