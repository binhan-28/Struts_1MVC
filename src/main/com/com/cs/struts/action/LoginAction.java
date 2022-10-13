package com.cs.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForward;

import com.cs.struts.form.LoginForm;
import com.cs.struts.logic.Login;
import com.cs.struts.util.ValidateUtils;

public class LoginAction extends Action {

    private final static String SUCCESS = "success";
    private final static String FAILURE = "failure";
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LoginForm loginForm = (LoginForm) form;
        ActionForward forward = new ActionForward();
        ActionMessage message = null;
        ActionMessages messages = new ActionMessages();
        
        String userID = loginForm.getUserID();
        String password = loginForm.getPassword();
        
        boolean result = Login.checkUserExist(userID, password);
        		
        
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
        		HttpSession session = request.getSession();
        		
        		loginForm.reset(mapping, request);
        		forward = mapping.findForward(SUCCESS);
        	} else {
				message = new ActionMessage("error.login.required");
				messages.add("invalid", message);
				saveMessages(request, messages);
				loginForm.reset(mapping, request);
				forward = mapping.findForward(FAILURE);
			}
        }
        return forward;
    }
}
