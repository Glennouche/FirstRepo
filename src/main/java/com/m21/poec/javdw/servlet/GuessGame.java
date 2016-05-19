package com.m21.poec.javdw.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GuessGame
 */
@WebServlet("/Guess")
public class GuessGame extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SERVLET_COOKIE_NAME = "GuessCookie"; 
	private static Random ran = new Random();
	private static int numberToGuess;
	private static final Logger LOGGER = Logger.getLogger(GuessGame.class.getName());
	private String validateString = "";
	private boolean recompense = false;
	private String urlImage = "<img src=\"http://avatarsetcomp.com/wallpapers/femmes_autos/Mustang_Sexy_Girl_m.jpg\" alt=\"Elle est pas belle ma ford mustang ??\">";
	private Map<String, Integer> localMap = new ConcurrentHashMap<>();
	

	/*public GuessGame() {
		
	}*/
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	private void getGuessCookie(Cookie[] cookies,HttpServletResponse response) {
		UUID myUUID = UUID.randomUUID();
		Cookie ReturnMonCookie;
		for(int i=0; i < cookies.length; i++) {
			ReturnMonCookie = cookies[i];
			if(ReturnMonCookie.getName().equals(SERVLET_COOKIE_NAME)) {
				// cookie has been found check if value exist in hash map or generate another one
				if(localMap.containsKey(ReturnMonCookie.getValue()))
				{
					numberToGuess = localMap.get(ReturnMonCookie.getValue());
					return;
				}
				else
				{
					localMap.put(ReturnMonCookie.getValue(), numberToGuess);
					response.addCookie(ReturnMonCookie);
					return;
				}	
			}
		}
		ReturnMonCookie = new Cookie(SERVLET_COOKIE_NAME,myUUID.toString());
		response.addCookie(ReturnMonCookie);
	}
	
	protected void manageCookies(HttpServletRequest request, HttpServletResponse response){
		Cookie[] cookies = request.getCookies();	
		// If no cookies present, create a new, add to hashmap and send it to user
		if( cookies == null) {
			UUID myUUID = UUID.randomUUID();
			Cookie MonCookie = new Cookie(SERVLET_COOKIE_NAME, myUUID.toString());
			response.addCookie(MonCookie);
			localMap.put(myUUID.toString(), numberToGuess);
		}else {
			// If cookies present get the numberToBeGuessed associated
				getGuessCookie(cookies,response);			
			}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		validateString = "Premier Essai";
		numberToGuess = (new Random().nextInt(100));
		manageCookies(request,response);
		renderForm(request,response,validateString,false);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//LOGGER.log(Level.INFO, "Received POST Request");
		//LOGGER.log(Level.INFO, "parameters: " + request.getParameter("number"));
		
		String number = request.getParameter("number");
			
		int userResponse;
		recompense = false;

		if(number != null && number.matches( "[0-9]+"))
		{
			int usernumber = Integer.parseInt(number);
		    if(numberToGuess == usernumber) {
		    	recompense = true;
		    	validateString = "Gagne petit filou";
		    	
		    }
		    else if(usernumber > 100) {
		    	validateString = "Tu en fait des caisse petit filou le nombre doit etre inferieur a 100";
		    }
		    else if(usernumber > numberToGuess ) {
		    	validateString = " Ton nombre est trop grand ";
		    }
		    else if(usernumber < numberToGuess) {
		    	validateString = "Ton nombre est trop petit";
		    }
		}
		else {
			validateString = "Bien joue petit filou, mais je ne prends que des nombres !!!";
		}
		renderForm(request,response,validateString,recompense);
		
	}
	
	private void renderForm(HttpServletRequest request, HttpServletResponse response, String message, boolean recompense) throws IOException {
		response.getWriter()
		.append("<!DOCTYPE html>")
		.append("<html>")
		.append("<head>")
			.append("<meta charset=\"UTF-8\">")
			.append("<title>Guess a number</title>")
		.append("</head>")
		.append("<body>")
		.append(String.format("<h1> Bonjour devinez un chiffre entre 0 et 100 pour gagner une ford Mustang !!(%d)</h1>",numberToGuess))
			.append("<form method=\"POST\">")
			.append("<label for=\"number\">Number:</label>");
		if(request.getParameter("number") != null) {
			response.getWriter().append(String.format("<input id=\"number\" type=\"text\" name=\"number\" placeholder=\"Enter a number here\" value=\"%s\">", request.getParameter("number")));				
		} else {
			response.getWriter().append("<input id=\"number\" type=\"text\" name=\"number\" placeholder=\"Enter a number here\">");
		}
		
		response.getWriter()
				.append("<input type=\"submit\">")
				.append("</form>")
				.append(recompense?urlImage:"<br>");
		
		if(message != null) {
			response.getWriter().append(String.format("<p>%s</p>", message));
		}
		
		response.getWriter()
			.append("</body>")
			.append("</html>");	
	}


}
