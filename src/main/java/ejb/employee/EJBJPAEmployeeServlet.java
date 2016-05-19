package ejb.employee;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.m21.poec.javdw.employees.EmployeeJPA;

import ejb.hello.AnalyticsSingleton;

/**
 * Servlet implementation class JPAEmployeeServlet
 */
@WebServlet("/EJBJPAEmployeeServlet")
public class EJBJPAEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(EJBJPAEmployeeServlet.class.getName());
	
	
	/*@PersistenceUnit(unitName="EmployeesPU")
	private EntityManagerFactory emf; !!! AVEC les EJB on utilise pas ça*/
	
	@Inject
	private EmployeeService myEmployeeService; // Ceci est une vue client
	
	@Inject 
	private AnalyticsSingleton myAnalytics;
	
	
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
		//int LastResult = (page*10)-10+recordsPerPage;
		myAnalytics.hitPage(request.getRequestURI());
		            
		List<EmployeeJPA> employees = myEmployeeService.findAll(FirstResult,recordsPerPage);
		//OGGER.info(String.format("Found %d employees %s", employees.size(), employees));
		
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
