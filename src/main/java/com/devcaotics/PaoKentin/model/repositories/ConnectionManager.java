package com.devcaotics.PaoKentin.model.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager
{
	private static Connection connection = null;
	
	private static final String URL      = "jdbc:mysql://localhost:3306/pao_kentin?useUnicode=yes&characterEncoding=UTF-8";
	private static final String USER     = "root";
	private static final String PASSWORD = "root-web2";
	
	static Connection getCurrentConnection() throws SQLException
	{
		if (connection == null)
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		
		return connection;
	}
	
	static Connection getNewConnection() throws SQLException
	{
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}