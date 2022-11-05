package cs.struts.util;

import java.util.regex.Pattern;

import cs.struts.logic.Import;

public class ValidateUtils {
    public static final String DATE_PATTERN = "([12]\\d{3}/(0[1-9]|1[0-2])/(0[1-9]|[12]\\d|3[01]))";
    public static final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

	//check input 
		public static boolean isBlank(String value) {
			if (value == null || "".equals(value.trim())) {
					return true;
				}
				return false;
		}
		
		//date check valid
		public static boolean isValidDate(final String date) {
			if (date == null) {
			    return false;
			}
		    return Pattern.matches(DATE_PATTERN, date);
		}
		//email check valid
        public static boolean isValidEmail(final String email) {
            if (email == null) {
                return false;
            }
            return Pattern.matches(EMAIL_PATTERN, email);
        }
        
		//check condition search
		public static boolean isValidCondition (String customerName, String sex, String birthdayFrom, String birthdayTo) {
			if (ValidateUtils.isBlank(customerName) && ValidateUtils.isBlank(sex) && ValidateUtils.isBlank(birthdayFrom) && ValidateUtils.isBlank(birthdayTo)) {
				return true;
			}
			return false;
		}
		
		public static String isValideInput(Integer i, String customerName, String sex, String birthday, String email, String address) {
		    String error = "";
		    
		    if (ValidateUtils.isBlank(customerName)) {
		        error += "Line " + i + " Customer Name is empty";
            } else {
                if (customerName.length() > 50) {
                    error += "Line " + i + " Customer Name is more than 50 character";
                } else {
                    if ("Male".equals(sex) || "Female".equals(sex)) {
                       if (ValidateUtils.isValidDate(birthday)) {
                           if (ValidateUtils.isValidEmail(email)) {
                               if (email.length() <= 40) {
                                   if (address.length() <= 256) {
                                       error += "insert";
                                } else {
                                    error += "Line " + i + " Address more than 256 character";                                             
                                }
                            } else {
                                error += "Line " + i + " Email more than 40 character"; 
                            }
                           }else {
                               error += "Line " + i + " Email is invalid"; 
                            }
                       }else {
                           error += "Line " + i + " Birthday is invalid"; 
                       }
                    } else {
                        error += "Line " + i + " Sex is invalid"; 
                    }
                }
            }           
		    return error;
		}
		
		public static String checkCustomerId (Integer i, String customerId, String customerName, String sex, String birthday, String email, String address) {
		    String listError = "";
		    if (!ValidateUtils.isBlank(String.valueOf(customerId))) {
                if (Import.checkCustomerExist(customerId)) {
                       listError += ValidateUtils.isValideInput(i, customerName, sex, birthday, email, address);          
                } else {
                    listError += "Line " + i + " Customer is not exist"; 
                }
            } else if (ValidateUtils.isBlank(String.valueOf(customerId))){
                listError += ValidateUtils.isValideInput(i, customerName, sex, birthday, email, address);
            }
		    return listError;
		}
//		public static void main(String[] args) {
////			String date = "2022/09/02";
////		    String emailString = "asdf@gmail.com";
//		    CustomerDto customerDto = new CustomerDto();		    		  		   
//		    
//			String isValid = ValidateUtils.checkCustomerId("", "asdfadfgadfgfdgfsdgsdfgsdfgsdfgsfdgdfadgafgdfgdfsdfgdf", "", "", "", "");
//			
//			System.out.println("isvalid;   "+isValid);
//		}
}
