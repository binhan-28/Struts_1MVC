package com.cs.struts.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	/**
	 * create connection
	 * 
	 * @author an-dlb-ttv
	 * @param dbUrl: database's url
	 * @param userName: username is used to login
	 * @param password: password is used to login
	 * @return connection
	 * */
	private static final String SERVER_NAME = "localhost\\SQLEXPRESS";
	private static final String DB_NAME = "CustomerSystem";
	private static final String PORT_NUMBER = "1433";
	private static final String USER_NAME = "sa";
	private static final String PASSWORD = "281163";
	
	
	public Connection getConnection()
			throws ClassNotFoundException, SQLException{
		Connection connection = null;
		String url = "jdbc:sqlserver://" + SERVER_NAME + ":" + PORT_NUMBER + ";databaseName=" + DB_NAME + ";user=" + USER_NAME + ";password=" + PASSWORD + ";encrypt = false";
		
		try {
			/**
			 * creating a connection by using the DriverManageer class
			 */
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //load driver
			//open connect to database
			connection= DriverManager.getConnection(url, USER_NAME, PASSWORD);
			
		} catch(SQLException | ClassNotFoundException ex) {
			ex.getMessage();
		}
		
		return connection;
	}
}
