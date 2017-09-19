package com.supportsys.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public Connection getConnection() {		
		
		try{
			
			DriverManager.registerDriver( new org.mariadb.jdbc.Driver());
//			DriverManager.registerDriver( new com.mysql.jdbc.Driver());
			String url = "jdbc:mariadb://127.0.0.1/spsys";	
			return DriverManager.getConnection(url,"root","info1999");
			
		}catch(SQLException e){			
			
			throw new RuntimeException("Error on exec!" + e);
			
		}

	}

}
