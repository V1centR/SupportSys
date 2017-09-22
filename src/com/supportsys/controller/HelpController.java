package com.supportsys.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
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

import org.hibernate.annotations.GenerationTime;

import com.supportsys.entity.Department;
import com.supportsys.entity.Help;
import com.supportsys.entity.Status;
import com.supportsys.entity.SupportUser;
import com.supportsys.entity.TypeHelp;
import com.supportsys.entity.User;


@WebServlet("/HelpController")
public class HelpController extends HttpServlet{
	
	/**
	 * Add help class
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		Connection db = null;
		PrintWriter out = response.getWriter();
		
		//Params_ form received
		String formMode = request.getParameter("formMode");
		String idHelp = request.getParameter("idHelp");
		String helpLabel = request.getParameter("helpLabel");
		String categoryHelp = request.getParameter("cat");
		String department_id = request.getParameter("department_id");
		String phone = request.getParameter("phone");
		String helpTxt = request.getParameter("desc");
		Calendar dataHelp = null;
		String tags = "Testes;Hibernate;JPA;MariaDb;Setembro2017";
		
		//convert to integer
		Integer categoryHelpInt = Integer.parseInt(categoryHelp);
		Integer departmentInt = Integer.parseInt(department_id);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("support");
		EntityManager em = emf.createEntityManager();
		
		//Session user
		User usuario = em.find(User.class, 1);
		
		out.println("<b>User:</b> " + usuario + "<br>");
		
		//SupportUser is nullable_
		SupportUser supportUser = em.find(SupportUser.class, 1);
		
		//Department
		Department department = em.find(Department.class, departmentInt);
		
		//Status active default value is 1, set in database
		Status status = em.find(Status.class, 1);
		
		//Status active default value is 1, set in database
		TypeHelp typeHelp = em.find(TypeHelp.class, categoryHelpInt);
		
		
		
//		Date date = new Date();
//		DateFormat dateHelpPattern = new SimpleDateFormat("YYYY-mm-dd hh:mm:ss");
		
		long now = Calendar.getInstance().getTimeInMillis();
		Timestamp ts = new Timestamp(now);
		
		

		
		//Date dateHelp = dateHelpPattern.format(date);
		
		
//		out.println("<b>User:</b> " + Calendar.getInstance().getTime());
		out.println("<b>User:</b> " + ts);
		
		try {
			
			Help help = new Help();
			
			help.setHelpLabel(helpLabel);
			help.setHelpTxt(helpTxt);
			help.setUser(usuario);
			help.setSupportUser(supportUser);
			help.setStatusBean(status);
			help.setDepartment(department);
			help.setDateHelp(ts);
			help.setTypeHelpBean(typeHelp);
			help.setTags(tags);
			
			em.getTransaction().begin();
			em.persist(help);
			em.getTransaction().commit();
//			
			out.println("<b>Chamado gravado com sucesso:</b> " + help.getId());
			
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
