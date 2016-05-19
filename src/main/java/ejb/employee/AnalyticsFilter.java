package ejb.employee;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import ejb.hello.AnalyticsSingleton;

/**
 * Servlet Filter implementation class AnalyticsFilter
 */
//@WebFilter("/AnalyticsFilter")
public class AnalyticsFilter implements Filter {

    /**
     * Default constructor. 
     */
	@Inject
	private AnalyticsSingleton myAnalytics;
	
	private static final Logger LOGGER  = Logger.getLogger(AnalyticsSingleton.class.getName());
	
    public AnalyticsFilter() {
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
		chain.doFilter(request, response);
		for(Entry<String, Integer> entry: myAnalytics.visitedPAges()) {
			//LOGGER.info(String.format("Page : %s, visited %d times" , entry.getKey(), entry.getValue()));
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
