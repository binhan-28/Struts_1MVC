package com.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.model.User;

/**
 *
 */
public class UserAction extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

	//đối tượng user
	User user = (User) form;
	user.setName("Hello Struts");
	user.setAge(10);
	return mapping.findForward("success"); //trả về trang view tên success
	}
}
