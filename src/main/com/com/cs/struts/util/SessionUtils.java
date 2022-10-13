package com.cs.struts.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {
	/**
	 * Supports session handling and login credentials
	 */
	public static void add(HttpServletRequest req, String name, Object value) {
		//get object session
		HttpSession session = req.getSession();
		session.setAttribute(name, value);
	}
	
	public static Object get(HttpServletRequest req, String userID) {
		//get object session
		HttpSession session = req.getSession();
		
		return session.getAttribute(userID);
	}
	
	//cancel session
	public static void invalidate(HttpServletRequest req) {
		//get object session
		HttpSession session = req.getSession();
		session.invalidate();
	}
	
	//check information user login
	public static boolean isLogin (HttpServletRequest req) {
		return get(req, "userID") != null;
	}
	
	//get login information and save in session
	public static String getLoginedUserID (HttpServletRequest req) {
		Object userID = get(req, "userID");
		if (userID == null) {
			return null;
		} else {
			return userID.toString();
		}
	}
}	
