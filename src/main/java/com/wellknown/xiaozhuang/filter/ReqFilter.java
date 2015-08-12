package com.wellknown.xiaozhuang.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellknown.xiaozhuang.utils.Logging;

public class ReqFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest oldRequest = (HttpServletRequest) request;
        HeaderRequest newRequest = new HeaderRequest(oldRequest);
        String contentType = newRequest.getHeader("content-type");
        //Logging.debug("old content-type = " + contentType);
        if (contentType != null && contentType.trim().startsWith("application/x-www-form-urlencoded")) {
        	newRequest.addHeader("content-type", "application/x-www-form-urlencoded");
        }
        //Logging.debug("new content-type = " + newRequest.getHeader("content-type"));
        chain.doFilter(newRequest, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
