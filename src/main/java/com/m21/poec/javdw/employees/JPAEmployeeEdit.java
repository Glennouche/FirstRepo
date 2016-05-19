package com.m21.poec.javdw.employees;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.m21.poec.javdw.employees.EmployeeJPA.Gender;


/**
 * Servlet implementation class JPAEmployeeEdit
 */
@WebServlet("/JPAEmployeeServlet/Edit/*")
public class JPAEmployeeEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(JPAEmployeeEdit.class.getName());
	@PersistenceUnit(unitName="EmployeesPU")
	private EntityManagerFactory emf;
	
	@Resource
	private UserTransaction utx;
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		LOGGER.info("Entering JPAEmployeeServlet/Edit");
		String id = (request.getPathInfo() == null ? null : request.getPathInfo().substring(1));
		
		if(!validate(id)){
			response.sendError(404);
			return;
		}
		
		EntityManager em = emf.createEntityManager();
		 EmployeeJPA employee = em.find(EmployeeJPA.class, Integer.parseInt(id));
			
		// EmployeeJPA employee = em.find(EmployeeJPA, Integer.parseInt(id));
		 if(employee == null)
		 {
			 response.sendError(404,"Page not Found");
		 }
		 
		request.setAttribute("employee", employee);
		request.getRequestDispatcher("/WEB-INF/EmployeeEditJPA.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	private boolean validate(String number) {
		return ((number != null) && ( number.matches("\\d+")));
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	String id = request.getParameter("id");
	try {
			utx.begin();
			EntityManager em = emf.createEntityManager();
			em.joinTransaction();
			EmployeeJPA employeeToUpdate = em.find(EmployeeJPA.class, Integer.parseInt(id));
			// employeeToUpdate est un objet managé donc les set effectué sreont 'commit'
			if(employeeToUpdate == null) {
				// quid de la transaction
				utx.rollback();
				LOGGER.info(String.format("unknow employee %d", Integer.parseInt(id)));
				response.sendError(404, "Page not found");
				return;
			}
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String gender = request.getParameter("gender");
			String birthDate = request.getParameter("birthDate");
			String hireDate = request.getParameter("hireDate");
			LOGGER.info(String.format("Input : %s, %s, %s %s %s %s", id, firstName,lastName, gender, birthDate, hireDate));
			employeeToUpdate.setId(Integer.parseInt(id)); 	// intégré en base lors du commit
			employeeToUpdate.setFirstName(firstName);		// intégré en base lors du commit
			employeeToUpdate.setLastName(lastName);			// intégré en base lors du commit
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			employeeToUpdate.setGender(gender.equals("F") ? Gender.F : Gender.M);	// intégré en base lors du commit
			
			// jj/mm/anne			
			try { // TODO to be managed
				employeeToUpdate.setBirthDate(sdf.parse(birthDate));
				employeeToUpdate.setHireDate(sdf.parse(hireDate));
			} catch (ParseException e2) {
				// TODO transaction
				//Réafficher le formulaire
				utx.rollback();
				throw new RuntimeException("Error with date, not yet implemented");
			}
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

}
