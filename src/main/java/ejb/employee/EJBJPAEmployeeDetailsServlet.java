package ejb.employee;

import java.io.IOException;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.inject.Inject;
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
@WebServlet("/EJBJPAEmployeeServlet/Show/*")
public class EJBJPAEmployeeDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(EJBJPAEmployeeDetailsServlet.class.getName());
	
	/*@PersistenceUnit(unitName="EmployeesPU")
	private EntityManagerFactory emf;
	
	@Resource
	private UserTransaction utx;*/
	
	@Inject
	private EmployeeService myEmployeeService;
	
	@Inject
	private AnalyticsSingleton myAnalytics;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = (request.getPathInfo() == null ? null : request.getPathInfo().substring(1));
		EmployeeJPA employee = new EmployeeJPA();
		myAnalytics.hitPage(request.getRequestURI());
		if(!validate(path)){
			response.sendError(404);
			return;
		}
		
		employee = myEmployeeService.findEmployeeDetails(path);
		
		
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
