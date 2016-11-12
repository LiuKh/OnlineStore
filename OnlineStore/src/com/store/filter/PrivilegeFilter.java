package com.store.filter;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.store.domain.Function;
import com.store.domain.Role;
import com.store.domain.User;
import com.store.service.BusinessService;
import com.store.service.impl.BusinessServiceImpl;

public class PrivilegeFilter implements Filter {

	private BusinessService bs = new BusinessServiceImpl();
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		
		try {
			request = (HttpServletRequest) arg0;
			response = (HttpServletResponse) arg1;
		} catch (Exception e) {
			throw new ServletException("no HTTP request or response");
		}
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if(user == null){
			request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
			return;
		}
		
		Set<Function> set = new HashSet();
		
		List<Role> role_list = bs.findRolesByUser(user);
		
		for(Role role : role_list){
			List<Function> func_list = bs.findFunctionByRole(role);
			set.addAll(func_list);
		}
		
		for(Function f : set){
			System.out.println(f.getUri());
		}
		String uri = request.getRequestURI();
		System.out.println(uri);
//		/***Important***/
//		if(request.getQueryString() != null){
//			uri = uri + "?" + request.getQueryString();
//		}
		uri = uri.replace(request.getContextPath(), "");
		System.out.println(uri);
		boolean permission = false;
		for(Function f : set){
			if(uri.equals(f.getUri())){
				permission = true;
				break;
			}
		}
		System.out.println(permission);
		if(!permission){
			response.getWriter().write("No Permission! Redirect in 2 seconds");
			Date date = new Date();
			response.setHeader("Refresh", "2:URL=" + request.getContextPath() + "/adminLogin.jsp" + "?time=" + date.toLocaleString() + System.nanoTime());
			return;
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
