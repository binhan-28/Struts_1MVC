package com.cs.struts.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class ValidateUtils {
	//check input 
		public static boolean isBlank(String value) {
			if (value == null || "".equals(value.trim())) {
					return true;
				}
				return false;
		}
		
		//date check valid
		public static boolean isValidDate(final String date) {
			boolean isValid = false;
			
			try {
				// ResolverStyle.STRICT for 30, 31 days checking, and also leap year.
	            LocalDate.parse(date,
	                    DateTimeFormatter.ofPattern("uuuu-M-d")
	                            .withResolverStyle(ResolverStyle.STRICT)
	            );

	            isValid = true;
			}catch (DateTimeParseException e) {
	            e.printStackTrace();
	            isValid = false;
	        }
			return isValid;
		}
		
		//check condition search
		public static boolean isValidCondition (String customerName, String sex, String birthdayFrom, String birthdayTo) {
			if (ValidateUtils.isBlank(customerName) && ValidateUtils.isBlank(sex) && ValidateUtils.isBlank(birthdayFrom) && ValidateUtils.isBlank(birthdayTo)) {
				return true;
			}
			return false;
		}
//		public static void main(String[] args) {
//			String date = "2022-09-02";
//			boolean isValid = ValidateUtils.isValidDate(date);
//			
//			System.out.println("isvalid;   "+isValid);
//		}
}
