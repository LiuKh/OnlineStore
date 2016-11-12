package com.store.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetCharacterEncodingFilter implements Filter {

	private FilterConfig filterConfig = null;
	
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = null;
		HttpServletResponse resp = null;
		
		try {
			req = (HttpServletRequest) request;
			resp = (HttpServletResponse) response;
		} catch (Exception e) {
			throw new RuntimeException("not http request or response");
		}
		
		String encoding = filterConfig.getInitParameter("encoding");
		if(encoding == null)
			encoding = "UTF-8";
		
		req.setCharacterEncoding(encoding);
		resp.setCharacterEncoding(encoding);
		resp.setContentType("text/html;charset=" + encoding);
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		this.filterConfig = arg0;
	}

}
