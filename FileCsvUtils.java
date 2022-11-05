package cs.struts.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import cs.struts.dao.CustomerDao;
import cs.struts.dto.CustomerDto;

public class FileCsvUtils {
    public static ArrayList<String> readToCsv() throws IOException{
        ArrayList<String> listError = new ArrayList<String>();
        String listErrors = "";
        String fileName = "D:\\\\workspace\\\\Customer_20221104.csv";
        BufferedReader bufferedReader = null;
        try {
            FileReader file = new FileReader(fileName);
            bufferedReader = new BufferedReader(file);
            int i = 1;
            int idInsert = 0;
            int idUpdate = 0;
            String line = bufferedReader.readLine();//reading header
            while((line = bufferedReader.readLine()) != null) {
               i++;
                String[] fields = line.split(",");
                String customerId = fields[0];
                String customerName = fields[1];
                String sex = fields[2];
                String birthday = fields[3];
                String email = fields[4];
                String address = fields[5];
               
                listErrors = ValidateUtils.checkCustomerId(i, customerId, customerName, sex, birthday, email, address);
                
                CustomerDto customerDto = new CustomerDto();           
                customerDto.setCustomerName(customerName);
                customerDto.setSex(sex);
                customerDto.setBirthday(birthday);
                customerDto.setEmail(email);
                customerDto.setAddress(address);

                if ("update".equals(listErrors)) {
                    idUpdate++;
                } else if ("insert".equals(listErrors)){
                    idInsert++;
                } else {
                    listError.add(listErrors);
                }
            }
            if ((idInsert + idUpdate) == (i-1)) {
                return null;
            } else {
                return listError;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bufferedReader.close();
        }
        return listError;
    }
    
    public static ArrayList<String> importToCsv() throws IOException{
        ArrayList<String> listResult = new ArrayList<String>();
        ArrayList<String> listErrors = new ArrayList<String>();
        listErrors = readToCsv();
        String fileName = "D:\\\\workspace\\\\Customer_20221104.csv";
        BufferedReader bufferedReader = null;
        try {
            FileReader file = new FileReader(fileName);
            bufferedReader = new BufferedReader(file);
            int i = 1;
//            CustomerDao customerDao = new CustomerDao();
            String line = bufferedReader.readLine();//reading header
            while((line = bufferedReader.readLine()) != null) {
               i++;
                String[] fields = line.split(",");
                String customerId = fields[0];
                String customerName = fields[1];
                String sex = fields[2];
                String birthday = fields[3];
                String email = fields[4];
                String address = fields[5];               
                
                CustomerDto customerDto = new CustomerDto();           
                customerDto.setCustomerName(customerName);               
                if ("Male".equals(sex) || "Female".equals(sex))
                if ("Male".equals(sex)) {
                    customerDto.setSex("0");
                } else if ("Female".equals(sex)){
                    customerDto.setSex("1");
                }
                customerDto.setBirthday(birthday);
                customerDto.setEmail(email);
                customerDto.setAddress(address);
                if (listErrors == null) {
                    if (!ValidateUtils.isBlank(String.valueOf(customerId))) {
                        CustomerDao.updateCustomer(customerDto, 1);
                        listResult.add("Update Line: " + i + " success");
                    } else {
                        CustomerDao.insertCustomer(customerDto, 1);
                        listResult.add("Insert Line: " + i + " success");
                    }
                }else {
                    listResult = listErrors;
                }
               }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bufferedReader.close();
        }
        return listResult;
    }
    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<String>();
        list = importToCsv();
        System.out.println(list);
        
    }
}
