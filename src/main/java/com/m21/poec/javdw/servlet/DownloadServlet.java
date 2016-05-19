package com.m21.poec.javdw.servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/Download")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger LOGGER = Logger.getLogger(DownloadServlet.class.getName());
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String file = request.getParameter("file");
			
		LOGGER.info(String.format("Parameters : %s",file));
		if(file == null) {
			LOGGER.info("File is null");
			return;
		}
		
		Path filePath = Paths.get(String.format("%s%s%s", FileManagerServlet.UP_DIRECTORY,File.separator,file));
		
		if(Files.notExists(filePath)) {
			LOGGER.info(String.format("%s File doesn't exist",filePath));
			return;
		}
		LOGGER.info(String.format("Parameters : %s",filePath));
		String contentType = Files.probeContentType(filePath);
		if(contentType == null) {
			contentType = "application/octet-stream";
		}
		response.addHeader("Content-Type", contentType);
		Files.copy(filePath, response.getOutputStream());	
	}

}
