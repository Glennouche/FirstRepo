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
 * Servlet implementation class JPAEmployeeCreate
 */
@WebServlet("/JPAEmployeeServlet/Create/")
public class JPAEmployeeCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(JPAEmployeeCreate.class.getName());
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
		LOGGER.info("Entering JPAEmployeeServlet/Create");
		request.getRequestDispatcher("/WEB-INF/EmployeeCreateJPA.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String id = request.getParameter("id");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String gender = request.getParameter("gender");
		String birthDate = request.getParameter("birthDate");
		String hireDate = request.getParameter("hireDate");
		
		
		LOGGER.info(String.format("Input : %s, %s, %s %s %s %s", id, firstName,lastName, gender, birthDate, hireDate));
		
		EmployeeJPA employee = new EmployeeJPA();
		employee.setId(Integer.parseInt(id));
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		
		// jj/mm/anne
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		employee.setGender(gender.equals("F") ? Gender.F : Gender.M);
		
		try { // TODO to be managed
			employee.setBirthDate(sdf.parse(birthDate));
			employee.setHireDate(sdf.parse(hireDate));
		} catch (ParseException e2) {
			//Réafficher le formulaire
			throw new RuntimeException("Error with date, not yet implemented");
		}
		
		try {
			utx.begin();
			EntityManager em = emf.createEntityManager();
			em.joinTransaction();
			em.persist(employee);
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
