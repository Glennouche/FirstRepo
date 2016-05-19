package ejb.employee;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.inject.Inject;
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
@WebServlet("/EJBJPAEmployeeServlet/Delete/*")
public class EJBJPAEmployeeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(EJBJPAEmployeeDeleteServlet.class.getName());
	
	@Inject
	private EmployeeService myEmployeeService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = (request.getPathInfo() == null ? null : request.getPathInfo().substring(1));
	
		if(!validate(id)){
			response.sendError(404);
			return;
		}
		
		myEmployeeService.deleteEmployee(id);
		response.sendRedirect("http://127.0.0.1:8080/hello-webapp-0.1/EJBJPAEmployeeServlet");
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
