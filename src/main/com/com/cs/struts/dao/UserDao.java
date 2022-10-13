package com.cs.struts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.cs.struts.connect.DBConnect;

public class UserDao {
	static Connection conn = null; //open connect to SQL server
	static PreparedStatement preparedStatement = null; //execute query SQL in JDBC API
	static ResultSet result = null; //receive result return
	  
	public static int getAccount(String userID, String password) { 
		int count = 0; 
		try {
			String query = "SELECT COUNT(*) AS CNT "
					+ "FROM MSTUSER "
					+ "WHERE DELETE_YMD IS NULL "
					+ "AND USERID = ? AND PASSWORD = ? "
					+ "GROUP BY USERID";
			conn = new DBConnect().getConnection();//connect to database CustomerSystem
			//create statment to insert MSTUSER
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, userID);
			preparedStatement.setString(2, password);
			result = preparedStatement.executeQuery(); //execute query in database
			
			//show data
			while (result.next()) {
				count = result.getInt(1);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	public String getUserName(String userID, String password) {
		String userName = "";	
		try {
			String query = "SELECT USERNAME "
					+ "FROM MSTUSER "
					+ "WHERE DELETE_YMD IS NULL "
					+ "AND USERID = ? AND PASSWORD = ?";
			conn = new DBConnect().getConnection();//open connect to sql
			//create statment to insert MSTUSER
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, userID);
			preparedStatement.setString(2, password);
			result = preparedStatement.executeQuery(); //execute query in database
			
			//show data
			while (result.next()) {
				userName = result.getString(1);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return userName;
	}
}
