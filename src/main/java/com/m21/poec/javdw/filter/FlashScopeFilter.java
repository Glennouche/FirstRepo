package com.m21.poec.javdw.filter;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class FlashScopefilTer
 */
//@WebFilter("/filemanager/*")
public class FlashScopeFilter implements Filter {
	
	private static Logger LOGGER = Logger.getLogger(FlashScopeFilter.class.getName());
	static final String FLASH_KEY = "flash";
	static final String FLASH_FRESH_KEY = "flash_fresh";
	
    /**
     * Default constructor. 
     */
    public FlashScopeFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		// pass the request along the filter chain
		
		
	
		if(request instanceof HttpServletRequest)
		{
			HttpServletRequest httpservlet = (HttpServletRequest) request;
			Object flash = httpservlet.getSession().getAttribute(FLASH_KEY);
			if(flash != null) {
				//LOGGER.info("in SESSION !!!!!!");
				httpservlet.setAttribute(FLASH_KEY, flash);
				httpservlet.getSession().removeAttribute(FLASH_KEY);
			}
		}
		// Propagation de la requête à la Servlet appelée <=>
		chain.doFilter(request, response);
		
		//if(request instanceof HttpServletResponse) {
			if(request.getAttribute(FLASH_KEY)!= null) {
				//LOGGER.info("in REQUEST !!!!!!");
				HttpServletRequest httpservlet = (HttpServletRequest) request;
				
				Object flash = httpservlet.getAttribute(FLASH_KEY);
				if(flash!=null) {
					httpservlet.getSession().setAttribute(FLASH_KEY, httpservlet.getAttribute(FLASH_KEY));
				}
			}
		//}
			/*if(response != null) {
				LOGGER.info("RESPONSE !!!!!!");
				request.removeAttribute("flash");
			}
			
			if(request != null) {
				LOGGER.info("REQUEST !!!!!!");
			}*/
			
	
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
