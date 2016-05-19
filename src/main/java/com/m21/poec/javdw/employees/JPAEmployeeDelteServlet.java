package com.m21.poec.javdw.employees;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 * Servlet implementation class JPAEmployeeDelteServlet
 */
@WebServlet("/JPAEmployeeServlet/Delete/*")
public class JPAEmployeeDelteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(JPAEmployeeDelteServlet.class.getName());
	@PersistenceUnit(unitName="EmployeesPU")
	private EntityManagerFactory emf;
	
	@Resource
	private UserTransaction utx;   
    /**
     * @see HttpServlet#HttpServlet()
     */
 
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String id = (request.getPathInfo() == null ? null : request.getPathInfo().substring(1));
	
		if(!validate(id)){
			response.sendError(404);
			return;
		}
		
		try {
			utx.begin();
			EntityManager em = emf.createEntityManager();
			em.joinTransaction();
			
			EmployeeJPA employee = em.find(EmployeeJPA.class, Integer.parseInt(id));
			// Ou laors DELETE direct via l'em avec Native querry
			
			if(employee == null) {
				LOGGER.info(String.format("unknow employee %d", id));
				response.sendError(404, "Page not found");
			}
			em.remove(employee);
			
			utx.commit();
			response.sendRedirect("http://127.0.0.1:8080/hello-webapp-0.1/JPAEmployeeServlet");

		} catch (NotSupportedException
				| SystemException 
				| SecurityException 
				| IllegalStateException
				| RollbackException
				| HeuristicMixedException
				| HeuristicRollbackException e) {
			LOGGER.log(Level.INFO, "Transaciton failed ",e);
			// TODO 
			try {
				utx.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				LOGGER.log(Level.INFO, "Rollback failed ", e1);
			}
		}
		
		
	}
	
	private boolean validate(String number) {
		return ((number != null) && ( number.matches("\\d+")));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
