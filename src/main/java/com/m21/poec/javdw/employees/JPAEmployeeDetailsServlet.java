package com.m21.poec.javdw.employees;

import java.io.IOException;
import java.text.ParseException;
import java.util.Timer;
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
 * Servlet implementation class JPAEmployeeServlet
 */
@WebServlet("/JPAEmployeeServlet/Show/*")
public class JPAEmployeeDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(JPAEmployeeDetailsServlet.class.getName());
	
	@PersistenceUnit(unitName="EmployeesPU")
	private EntityManagerFactory emf;
	
	@Resource
	private UserTransaction utx;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = (request.getPathInfo() == null ? null : request.getPathInfo().substring(1));
		EmployeeJPA employee = new EmployeeJPA();
		
		if(!validate(path)){
			response.sendError(404);
			return;
		}
		
		try {
				utx.begin();
				EntityManager em = emf.createEntityManager();
				// On veut l'employee et ses salaires
				//EmployeeJPA employee = em.find(EmployeeJPA.class, Integer.parseInt(path)); 
				/*employee = em.createQuery("SELECT e FROM EmployeeJPA e LEFT JOIN FETCH e.salaries"
						+" LEFT JOIN FETCH e.deptAssociations WHERE e.id = :id", EmployeeJPA.class) // On ne met pas la condition de jointure  
								.setParameter("id", Integer.parseInt(path))
								.getSingleResult();// TODO try catch no result*/
				//OU
		
				employee = em.createQuery("SELECT e FROM EmployeeJPA e LEFT JOIN FETCH e.deptAssociations WHERE e.id = :id", EmployeeJPA.class) // On ne met pas la condition de jointure  
								.setParameter("id", Integer.parseInt(path))
								.getSingleResult();
				
				employee.getDepart_history().size();
				employee.getSalaries().size();
				utx.commit();
				
		} catch ( NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
			response.sendError(500, String.format("Error accessing DB: %s",e));
			try {
				utx.rollback();
			} catch ( IllegalStateException | SecurityException | SystemException e2) {
				response.sendError(404, String.format("Error loading exception : %s",e2));
			}
		}
		
		
		// EmployeeJPA employee = em.find(EmployeeJPA, Integer.parseInt(id));
		 if(employee == null)
		 {
			 response.sendError(404,"Page not Found");
			 return;
		 }
		 LOGGER.info(String.format("Employee", employee));
		request.setAttribute("employee", employee);
		request.getRequestDispatcher("/WEB-INF/EmployeeDetailsJPA.jsp").forward(request, response);
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
