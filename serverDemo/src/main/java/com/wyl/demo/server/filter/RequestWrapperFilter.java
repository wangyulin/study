package com.wyl.demo.server.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wyl.demo.server.controller.HelloController;

public class RequestWrapperFilter implements Filter {
	
	@SuppressWarnings("unused")
	private final static Logger LOGGER = LoggerFactory.getLogger("access");
	@SuppressWarnings("unused")
	private final static Logger CONSOLE_LOGGER = LoggerFactory.getLogger(HelloController.class); 
	
	public void init(FilterConfig config) throws ServletException {
		// nothing goes here
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws java.io.IOException, ServletException {
		MultiReadHttpServletRequest requestWrapper = new MultiReadHttpServletRequest((HttpServletRequest) request);
		// Pass request back down the filter chain
		
		chain.doFilter(requestWrapper, response);
	}

	public void destroy() {
		/*
		 * Called before the Filter instance is removed from service by the web
		 * container
		 */
	}
}