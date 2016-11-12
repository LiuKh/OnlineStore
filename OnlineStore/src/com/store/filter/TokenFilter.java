package com.store.filter;

import java.io.IOException;
import java.net.HttpRetryException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TokenFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request;
		HttpServletResponse response;
		
		try {
			request = (HttpServletRequest) arg0;
			response = (HttpServletResponse) arg1;
		} catch (Exception e) {
			throw new ServletException("no http request or response");
		}
		
		String pageToken = request.getParameter("token");
		HttpSession session = request.getSession();
		String sessionToken = (String) session.getAttribute("token");
		if(pageToken != null){
			if(pageToken != sessionToken){
				response.getWriter().write("Error in resubmit!");
				return;
			}else{
				session.removeAttribute("token");
			}
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
