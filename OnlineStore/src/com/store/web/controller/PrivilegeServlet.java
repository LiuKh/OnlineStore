package com.store.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.domain.User;
import com.store.service.BusinessService;
import com.store.service.impl.BusinessServiceImpl;


public class PrivilegeServlet extends HttpServlet {
	
	private BusinessService bs = new BusinessServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = bs.userLogin(username, password);
		if(user == null){
			response.getWriter().write("Not Administer! Redirect in 2 seconds");
			response.setHeader("Refresh", "2;URL=" + request.getContextPath() + "/adminLogin.jsp");
			return;
		}
		
		request.getSession().setAttribute("user", user);
		response.sendRedirect(request.getContextPath() + "/management/index.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
