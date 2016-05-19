package com.m21.poec.javdw.servlet;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.m21.poec.javdw.filter.FlashMessageHelper;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/Upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger LOGGER = Logger.getLogger(UploadServlet.class.getName());  
	static final String UP_DIRECTORY = "D:\\Upload\\";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			LOGGER.info(String.format("Attrributes : %s", request.getAttributeNames()));
			// TODO Auto-generated method stub
			Collection<Part> parts= request.getParts();
			// Pour parcourir une Collecion (<=> liste non ordonée) on fait appel à un itérateur
			for (Iterator iterator = parts.iterator(); iterator.hasNext();) {
				Part part = (Part) iterator.next();
				LOGGER.info(part.toString());
				LOGGER.info(part.getName());
				LOGGER.info(part.getSubmittedFileName());
				LOGGER.info(String.format("Size : %d kb\n", part.getSize()));
				
				if(Files.notExists(Paths.get(UP_DIRECTORY))){
					LOGGER.info(String.format("Le dossier %s n'existe pas", UP_DIRECTORY));
					Files.createDirectory(Paths.get(UP_DIRECTORY));
				}
				
				try {
					Files.copy(part.getInputStream(), Paths.get(UP_DIRECTORY+"\\"+part.getSubmittedFileName()));
				} catch(FileAlreadyExistsException ex) {
					//response.setStatus(HttpServletResponse.SC_CONFLICT);
					//request.setAttribute("flash", "fichier déjà uploadé");
					FlashMessageHelper.addFlashMessage(request, "fichier déjà uploadé");
					response.sendRedirect("FileManager");
					return;
				}
			//FileManagerServlet.renderResponse(request, response);
			response.sendRedirect("FileManager");
			}

	}
}
