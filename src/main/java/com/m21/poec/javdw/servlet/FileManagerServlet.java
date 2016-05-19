package com.m21.poec.javdw.servlet;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileManagerServlet
 */
@WebServlet("/FileManager")

public class FileManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger LOGGER = Logger.getLogger(FileManagerServlet.class.getName());
	static final String UP_DIRECTORY = "D:\\Upload\\";
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		renderResponse(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	
	
	 private static void renderResponse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		List<Path> dirFiles = new ArrayList<>();
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(UP_DIRECTORY))) {
		    for (Path file: stream) {
		    	dirFiles.add(file);
		    }
		} catch (IOException | DirectoryIteratorException ex) {
		    // IOException can never be thrown by the iteration.
		    // In this snippet, it can only be thrown by newDirectoryStream.
		    //LOGGER.log(Level.INFO, "Ca a cacaté !!", ex);
		}
		request.setAttribute("files", dirFiles);
		request.getRequestDispatcher("/WEB-INF/FileManager.jsp").forward(request, response);
		
	}
}
