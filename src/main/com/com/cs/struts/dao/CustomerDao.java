package com.cs.struts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cs.struts.connect.DBConnect;
import com.cs.struts.dto.CustomerDto;

public class CustomerDao {
	 static Connection conn = null; //open connect to SQL server
	  static PreparedStatement preparedStatement = null; //execute query SQL in JDBC API
	  static ResultSet result = null; //receive result return
	
	public ArrayList<CustomerDto> getAllCustomer(Integer page){
		ArrayList<CustomerDto> listCustomer = new ArrayList<CustomerDto>();
		String query = "SELECT CUSTOMER_ID, CUSTOMER_NAME, SEX, BIRTHDAY, ADDRESS "
				+ "FROM MSTCUSTOMER "
				+ "WHERE DELETE_YMD IS NULL "
				+ "ORDER BY CUSTOMER_ID "
				+ "OFFSET ? ROWS FETCH NEXT 15 ROWS ONLY";
				
		try {
			conn = new DBConnect().getConnection();//open connect to sql
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, (page-1)*15);
			result = preparedStatement.executeQuery();

			while (result.next()) {
				CustomerDto t002Dto = new CustomerDto(); 
				
				int customerId = result.getInt("CUSTOMER_ID");
				String customerName = result.getString("CUSTOMER_NAME");
				String sex = result.getString("SEX");
				String birthday = result.getString("BIRTHDAY");
				String address = result.getString("ADDRESS");
				
				t002Dto.setCustomerId(customerId);
				t002Dto.setCustomerName(customerName);
				t002Dto.setSex(sex);
				t002Dto.setBirthday(birthday);
				t002Dto.setAddress(address);
				
				listCustomer.add(t002Dto);

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return listCustomer;
	}

	/**
	 * get the total number of lines when executing the query
	 * @param 
	 * @return total customer
	 * */
	//count customer in database
	public static Integer getTotalCustomer() {
		String query = "SELECT COUNT(*) "
				+ "FROM MSTCUSTOMER "
				+ "WHERE DELETE_YMD IS NULL ";
		
		try {
			conn = new DBConnect().getConnection();//open connect to sql
			preparedStatement = conn.prepareStatement(query);
			result = preparedStatement.executeQuery();
			
			result.next();
			return result.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
//	public List<T002Dto> padingCustomer(int index){
//		List<T002Dto> list = new ArrayList<>();
//		String query = "SELECT CUSTOMER_ID, CUSTOMER_NAME, SEX, BIRTHDAY, ADDRESS"
//				+ " FROM MSTCUSTOMER "
//				+ "WHERE DELETE_YMD IS NULL "
//				+ "ORDER BY CUSTOMER_ID " 
//				+ "OFFSET ? ROWS FETCH NEXT 15 ROWS ONLY";
//		
//		try {
//			conn = new DBConnect().getConnection();//open connect to sql
//			preparedStatement = conn.prepareStatement(query);
//			preparedStatement.setInt(1, (index-1)*15);
//			result = preparedStatement.executeQuery();
//			
//			while (result.next()) {
//				list.add(new T002Dto(result.getInt("CUSTOMER_ID"),
//						result.getString("CUSTOMER_NAME"),
//						result.getString("SEX"),
//						result.getString("BIRTHDAY"),
//						result.getString("ADDRESS")));
//			}
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
	/**
	 * search customer by id
	 * @param id of customer
	 * @return customer
	 */
	public static CustomerDto searchCustomerById(Integer customerid) {
		String query = "SELECT CUSTOMER_ID, CUSTOMER_NAME, SEX, BIRTHDAY, ADDRESS "
				+ "FROM MSTCUSTOMER "
				+ "WHERE DELETE_YMD IS NULL "
				+ "AND CUSTOMER_ID = ?";

		try {
			conn = new DBConnect().getConnection();//open connect to sql
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, customerid);
			result = preparedStatement.executeQuery();
			
			while (result.next()) {
				CustomerDto t002Dto = new CustomerDto(); 
				
				int customerId = result.getInt("CUSTOMER_ID");
				String customerName = result.getString("CUSTOMER_NAME");
				String sex = result.getString("SEX");
				String birthday = result.getString("BIRTHDAY");
				String address = result.getString("ADDRESS");
				
				t002Dto.setCustomerId(customerId);
				t002Dto.setCustomerName(customerName);
				t002Dto.setSex(sex);
				t002Dto.setBirthday(birthday);
				t002Dto.setAddress(address);
				
				return t002Dto;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * get a list 15 customer
	 * @param conditionWhere
	 * @param pages being display
	 * @return list customer
	 */
	public static ArrayList<CustomerDto> searchCustomers(String conditionWhere, Integer page){
		ArrayList<CustomerDto> listCustomer = new ArrayList<CustomerDto>();
		StringBuilder query = new StringBuilder("SELECT CUSTOMER_ID, CUSTOMER_NAME, SEX, BIRTHDAY, ADDRESS ");
		
		query.append("FROM MSTCUSTOMER ");
		query.append("WHERE DELETE_YMD IS NULL ");
		query.append(conditionWhere);
		query.append("ORDER BY CUSTOMER_ID ");
		query.append("OFFSET ? ROWS FETCH NEXT 15 ROWS ONLY");
		try {
			conn = new DBConnect().getConnection();//open connect to sql
			preparedStatement = conn.prepareStatement(query.toString());
			preparedStatement.setInt(1, (page-1)*15);
			result = preparedStatement.executeQuery();

			while (result.next()) {
				CustomerDto t002Dto = new CustomerDto(); 
				
				int customerId = result.getInt("CUSTOMER_ID");
				String customerName = result.getString("CUSTOMER_NAME");
				String sex = result.getString("SEX");
				String birthday = result.getString("BIRTHDAY");
				String address = result.getString("ADDRESS");
				
				t002Dto.setCustomerId(customerId);
				t002Dto.setCustomerName(customerName);
				t002Dto.setSex(sex);
				t002Dto.setBirthday(birthday);
				t002Dto.setAddress(address);
				
				listCustomer.add(t002Dto);

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return listCustomer;
	}
	
//	public static void main(String[] args) {
//		Integer page = 1;
//		ArrayList<T002Dto> list = T002Dao.searchCustomers("AND CUSTOMER_NAME LIKE N'%a%' and sex = '0' and BIRTHDAY >= '1972-07-01' AND BIRTHDAY <= '1982-01-10' ", page);
//		Integer total = T002Dao.getTotalCustomer("AND CUSTOMER_NAME LIKE N'%a%' and sex = '0' and BIRTHDAY >= '1972-07-01' AND BIRTHDAY <= '1982-01-10' ");
//		for (T002Dto oDto : list) {
//			System.out.println(oDto.getCustomerName());
//		}
//		System.out.println("total" + total);
//	}
}
