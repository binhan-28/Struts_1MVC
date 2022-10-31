package cs.struts.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForward;

import cs.struts.dao.CustomerDao;
import cs.struts.dto.CustomerDto;
import cs.struts.form.LoginForm;
import cs.struts.logic.Login;
import cs.struts.util.ValidateUtils;

public class LoginAction extends Action {

    private final static String SUCCESS = "success";
    private final static String FAILURE = "failure";
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward forward = new ActionForward();
        try {
            LoginForm loginForm = (LoginForm) form;
            
            ActionMessage message = null;
            ActionMessages messages = new ActionMessages();
            
            String userID = loginForm.getUserID();
            String password = loginForm.getPassword();
            
            boolean result = Login.checkUserExist(userID, password);
            
            String button = request.getParameter("button");
            switch (button) {
                case "btnLogin":
                    if (ValidateUtils.isBlank(userID)) {
                        message = new ActionMessage("error.userID.required");
                        messages.add("userID", message);
                        saveMessages(request, messages);
                        loginForm.reset(mapping, request);
                        forward = mapping.findForward(FAILURE);
                        return forward;
                    }
                    if (ValidateUtils.isBlank(password)) {
                        message = new ActionMessage("error.password.required");
                        messages.add("password", message);
                        saveMessages(request, messages);
                        loginForm.reset(mapping, request);;
                        forward = mapping.findForward(FAILURE);
                    }
                    if (!ValidateUtils.isBlank(userID) && !ValidateUtils.isBlank(password)) {
                        if (result) {
                            forward = mapping.findForward(SUCCESS);
                        } else {
                            message = new ActionMessage("error.login.required");
                            messages.add("invalid", message);
                            saveMessages(request, messages);
                            loginForm.reset(mapping, request);
                            forward = mapping.findForward(FAILURE);
                        }
                    }
                    break;
                case "btnExport":
                    try {
                        
//                        PrintWriter out = response.getWriter();
                        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                        String timeStamp = dateFormat.format(new Date());
                        String filename = "D:\\\\workspace\\\\ServletBlank\\\\WebContent\\\\WEB-INF\\\\csv\\\\Customer_" + timeStamp + ".csv";
                       
                        
                        FileWriter fw = new FileWriter(filename);
                        fw.append("Customer ID");
                        fw.append(',');
                        fw.append("Customer Name");
                        fw.append(',');
                        fw.append("Sex");
                        fw.append(',');
                        fw.append("Birthday");
                        fw.append(',');
                        fw.append("Email");
                        fw.append(',');
                        fw.append("Address");
                        fw.append('\n');

                        List<CustomerDto> list = new ArrayList<CustomerDto>();
                        CustomerDao cusDao = new CustomerDao();
                        list = cusDao.getAllCustomer();
                        
                        for (CustomerDto cus : list) {
                          String customerID = String.valueOf(cus.getCustomerId());
                            fw.append(customerID);
                            fw.append(',');
                            fw.append(cus.getCustomerName());
                            fw.append(',');
                            if ("1".equals(cus.getSex())) {
                                fw.append("Female");
                            } else if ("0".equals(cus.getSex())) {
                                fw.append("Male");
                          }
                            fw.append(',');
                            fw.append(cus.getBirthday());
                            fw.append(',');
                            fw.append(cus.getEmail());
                            fw.append(',');
                            fw.append(cus.getAddress());        
                            fw.append('\n');
                      }       
                       
                        fw.flush();
                        fw.close();
//                        out.println("<b>Csv file Successfully created.</b>" + filename);
                        
                        FileInputStream in = 
                                new FileInputStream(new File("D:\\\\workspace\\\\ServletBlank\\\\WebContent\\\\WEB-INF\\\\csv\\\\Customer_" + timeStamp + ".csv"));
                        response.setContentType("text/csv");
                        String filenames = "Customer_" + timeStamp + ".csv";
                        response.setHeader("Content-Disposition", "attachment;filename=" + filenames);
                        
                        ServletOutputStream out = response.getOutputStream();
                        
                        byte[] outputByte = new byte[4096];
                        //copy binary content to output stream
                        while(in.read(outputByte, 0, 4096) != -1){
                            out.write(outputByte, 0, 4096);
                        }
                        in.close();
                        out.flush();
                        out.close();
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return forward;
    }
}
