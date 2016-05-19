package com.m21.poec.javdw.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 */

// Servlet Mapping
@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(HelloServlet.class.getName());

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		
		LOGGER.log(Level.INFO, "Received HTTP Request");
		LOGGER.log(Level.INFO, "queryString: " + request.getQueryString());
		LOGGER.log(Level.INFO, "parameters: ");
		
		Enumeration<String> parameters;
			
		for(parameters = request.getParameterNames();parameters.hasMoreElements();) {
			String paramName =parameters.nextElement();
			LOGGER.log(Level.INFO, String.format("\t %s:%s", paramName, request.getParameter(paramName)));
		}
		
		
		
		response.addHeader("Content-Type", "text/html");
		
		response.getWriter()
		.append("<!DOCTYPE html>")
		.append("<html>")
		.append("<head>")
			.append("<meta charset=\"UTF-8\">")
			.append("<title> Bonjour Servlet </title>")
		.append("</head>")
		.append("<body>")
			.append("Served at: ").append(request.getContextPath())
			// TODO Attention, ne pas afficher de données non filtrées en HTML
			// ex : <script> alert('coucou...')</script>
			//.append(String.format("<h1> Bonjour devinez un chiffre entre 0 et 100 </h1>"))
		.append("</body>")
		.append("</html>") ;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
