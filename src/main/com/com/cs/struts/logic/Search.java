package com.cs.struts.logic;

import java.io.IOException;
import java.util.ArrayList;

import com.cs.struts.dao.CustomerDao;
import com.cs.struts.dto.CustomerDto;
import com.cs.struts.util.ValidateUtils;

public class Search {
	public Integer getLimitPage() {
		int rowCount = CustomerDao.getTotalCustomer();
		if ((rowCount % 15) == 0) {
			return rowCount/15-1;
		}else {
			return rowCount/15;
		}
	}
	
	/**
	 * get limit page when execute search
	 * @param customerName
	 * @param sex
	 * @param birthdayFrom
	 * @param birthdayTo
	 * @return limit page
	 */
//	public Integer getLimitPage(String customerName, String sex,
//			String birthdaytFrom, String birthdayTo) {
//		int rowCount = CustomerDao.getTotalCustomer(
//				getConditionWhere(customerName, sex, birthdaytFrom, birthdayTo));
//		if ((rowCount % 15) == 0) {
//			return rowCount/15-1;
//		}else {
//			return rowCount/15;
//		}
//	}
	
	/**
	 * check birthday from and birthday to
	 * @param birthday from
	 * @param birthday to
	 * @throws IOException
	 * @return notification
	 */
	public String checkBirthday(String birthdayFrom, String birthdayTo) 
			throws IOException{
		if (!ValidateUtils.isBlank(birthdayFrom)) {
			if (!ValidateUtils.isValidDate(birthdayFrom)) {
				return "invalid birthday from";
			}
		}
		
		if (!ValidateUtils.isBlank(birthdayTo)) {
			if (!ValidateUtils.isValidDate(birthdayTo)) {
				return "invalid birthday to";
			}
		}

		if (ValidateUtils.isValidDate(birthdayFrom) 
				&& ValidateUtils.isValidDate(birthdayTo)) {
			if (birthdayFrom.compareTo(birthdayTo) > 0) {
				return "There is an error in the range input of Birthday";
			}
		}
		return null;
	}
	
	/**
	 * condition search sql
	 * @param customerName
	 * @param sex
	 * @param birthdayFrom
	 * @param birthdayTo
	 * @return conditionWhere
	 */
	public String getConditionWhere(String customerName, 
			String sex, String birthdatFrom, String birthdayTo) {
		//create condition
		StringBuilder conditionWhere = new StringBuilder();
		
		//check empty customerName
		if (!ValidateUtils.isBlank(customerName)) {
			conditionWhere.append("AND CUSTOMER_NAME LIKE N'%" + customerName + "%' ");
		}
		
		//check empty sex
		if (!ValidateUtils.isBlank(sex)) {
			if (sex.equals("Male")) {
				conditionWhere.append("AND SEX = '0' ");
			} else {
				conditionWhere.append("AND SEX = '1' ");
			}
		}
		
		//check birthday from
		if (!ValidateUtils.isBlank(birthdatFrom)) {
			conditionWhere.append("AND BIRTHDAY >= '" + birthdatFrom + "' ");
		}
		
		//check birthday to
				if (!ValidateUtils.isBlank(birthdayTo)) {
					conditionWhere.append("AND BIRTHDAY <= '" + birthdayTo + "' ");
				}
		return conditionWhere.toString();
	}
	
	/**
	 * get 15 customer when execute search
	 * @param page
	 * @param customerName
	 * @param sex
	 * @param birthdayFrom
	 * @param birthdayTo
	 * @return conditionWhere
	 */
	public ArrayList<CustomerDto> searchCustomer(String customerName, String sex,
			 String birthdayFrom, String birthdayTo, Integer page){
		return CustomerDao.searchCustomers(
				getConditionWhere(customerName, sex, birthdayFrom, birthdayTo), page);
	}
	
//	public static void main(String[] args) {
//		T002Logic t002Logic = new T002Logic();
//		Integer total = t002Logic.getLimitPage("'%a%", "1", "1972-07-01", "1982-01-10");
//		 
//		System.out.println("total   " + total);
//	}
}
