package com.store.tag;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.store.util.IdGenerator;

public class Token extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		String token = IdGenerator.generateGUID();
		PageContext pc =  (PageContext) getJspContext();
		HttpSession session = pc.getSession();
		session.setAttribute("token", token);
		
		pc.getOut().write("<input type='hidden' name='token' value='" + token + "'/>");
	}
}
