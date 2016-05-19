package com.m21.poec.javdw.employees;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JPAEmployeeServlet
 */
@WebServlet("/JPAEmployeeServlet")
public class JPAEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(JPAEmployeeServlet.class.getName());
	
	
	@PersistenceUnit(unitName="EmployeesPU")
	private EntityManagerFactory emf;
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int page = 1;
		int recordsPerPage = 10;
		if(request.getParameter("page") != null) {
		            page = Integer.parseInt(request.getParameter("page"));;
		}
		LOGGER.info(String.format("***************** PAGE %d ****************", page));
		
		int FirstResult = (page*10)-10;
		int LastResult = (page*10)-10+recordsPerPage;
		            
		EntityManager em = emf.createEntityManager();
		
		//em.createNativeQuery(arg0)		==> Permet de passer en paramètre du SQL pur
		//em.createNamedQuery(arg0, arg1) 	==>
		
		//em.createQuery(arg0)				==> 2 APIs : 
		// 										Criteria API 	= reuqêtes composées via des objets, approche programmatique
		//										JPQL			= requêtes exprimées sous la forme de String (qui utilisent des objets)(OO/VendorNeutral)
		
		// Ce n'est pas du SQL mais ça y ressemble on récupère une entitié à partir d'un objet, non d'une table
		//String jpqlQuery = "SELECT e_alias FROM EmployeeJPA e_alias";
		/*TypedQuery<EmployeeJPA> query = em.createQuery(EmployeeJPA.FIND_ALL_ORDER_BY_ID, EmployeeJPA.class)
														.setFirstResult(0)
														.setMaxResults(10);*/
		
		
		LOGGER.info(String.format("New Page with FirstResult = %d and LastResult = %d", FirstResult, LastResult));
		
		TypedQuery<EmployeeJPA> query = em.createNamedQuery("EmployeeJPA.FIND_ALL_ORDER_BY_ID", EmployeeJPA.class)
				.setFirstResult(FirstResult)
				.setMaxResults(recordsPerPage);
		
		List<EmployeeJPA> employees = query.getResultList();
		//LOGGER.info(String.format("Found %d employees %s", employees.size(), employees.toString()));
		
		request.setAttribute("employees", employees);
	    request.setAttribute("noOfPages", 10);
	    request.setAttribute("currentPage", page);
		request.getRequestDispatcher("/WEB-INF/EmployeesJPA.jsp").forward(request, response);
		//EmployeeJPA employee = em.find(EmployeeJPA.class, new Integer(10155)); //ne passe pas par les setters de la classe employeesJPA
		//LOGGER.info(employee.toString());
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
