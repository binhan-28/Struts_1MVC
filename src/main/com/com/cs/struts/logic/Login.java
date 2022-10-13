package com.cs.struts.logic;


import com.cs.struts.dao.UserDao;
import com.cs.struts.util.ValidateUtils;


/**
 * create check login
 * 
 * @author an-dlb-ttv
 * @param dbUrl: database's url
 * @param userName: username is used to login
 * @param password: password is used to login
 * @return status login
 * */

public class Login {
//	public static String checkLogin(String userID, String password) {
//		if (ValidateUtils.isBlank(userID)) {
//			return Constants.T001_ERROR_NOT_INPUT_USEID;
//		}
//		if (ValidateUtils.isBlank(password)) {
//			return Constants.T001_ERROR_NOT_INPUT_PASSWORD;
//		}
//		return "";
//	}
//	
	//check user exist
	public static boolean checkUserExist(String userID, String password) {
		boolean isValid = true;
		int count = UserDao.getAccount(userID, password);
		if (count != 1) {
			isValid = false;
		} else {
			isValid = true; //case login success
		}
		return isValid;
	}
//	
//	public static boolean checkUserIDLogin(String userID) {
//		boolean isValid = true;
//		if (ValidateUtils.isBlank(userID)) {
//			isValid = true;
//		} else {
//			isValid = false;
//		}
//		return isValid;
//	}
	
	public static boolean checkPasswordLogin(String password) {
		boolean isValid = true;
		if (ValidateUtils.isBlank(password)) {
			isValid = true;
		} else {
			isValid = false;
		}
		return isValid;
	}
}
