package ejb.employee;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.m21.poec.javdw.employees.EmployeeJPA;
import com.m21.poec.javdw.employees.EmployeeJPA.Gender;


/**
 * Servlet implementation class JPAEmployeeCreate
 */
@WebServlet("/EJBJPAEmployeeServlet/Create/")
public class EJBJPAEmployeeCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(EJBJPAEmployeeCreate.class.getName());
	
	
	@Inject
	private EmployeeService myEmployeeService;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		LOGGER.info("Entering JPAEmployeeServlet/Create");
		request.getRequestDispatcher("/WEB-INF/EmployeeCreateJPA.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String id = request.getParameter("id");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String gender = request.getParameter("gender");
		String birthDate = request.getParameter("birthDate");
		String hireDate = request.getParameter("hireDate");
		
		
		LOGGER.info(String.format("Input : %s, %s, %s %s %s %s", id, firstName,lastName, gender, birthDate, hireDate));
		
		if(!validate(id)){
			response.sendError(404);
			return;
		}
		
		// TODO Validate ohter fields here (!= NULL)
		EmployeeJPA employee = new EmployeeJPA();
		employee.setId(Integer.parseInt(id));
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		
		// jj/mm/anne
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try { // TODO to be managed
			employee.setBirthDate(sdf.parse(birthDate));
			employee.setHireDate(sdf.parse(hireDate));
		} catch (ParseException e2) {
			//Réafficher le formulaire
			throw new RuntimeException("Error with date, not yet implemented");
		}
		
		employee.setGender(gender.equals("F") ? Gender.F : Gender.M);
		
		try {
			myEmployeeService.createEmployee(employee);
		} catch (EJBException e) {
			// TODO afficher le formulaire
			/*Throwable t = unrollException(e, MySQLIntegrityConstraintViolationException.class);
			if(t!= null)
			{
				MySQLIntegrityConstraintViolationException em = (MySQLIntegrityConstraintViolationException) e;
			}*/
		/*}
	
	
		
		
		response.sendRedirect("http://127.0.0.1:8080/hello-webapp-0.1/EJBJPAEmployeeServlet");
		
		}*/
	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
			/*if(! validateFormFields(request)) {
				
				Valider tous les champs de l'employé
				
			}*/
			String id = request.getParameter("id");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String gender = request.getParameter("gender");
			String birthDate = request.getParameter("birthDate");
			String hireDate = request.getParameter("hireDate");
			if(!validate(id)){
				response.sendError(404);
				return;
			}
			
			// vérifier que l'id n'existe pas déjà en base
			/*if(myEmployeeService.employeeExist(id)){
				//TODO => affficher formulaire avec message d'erreur
				return;
			}*/
			
			// TODO Validate other fields here (!= NULL)
			EmployeeJPA employee = new EmployeeJPA();
			employee.setId(Integer.parseInt(id));
			
			if(firstName == null || firstName.trim().isEmpty())
			employee.setFirstName(firstName);
			employee.setLastName(lastName);
			employee.setGender(gender.equals("F") ? Gender.F : Gender.M);
			// jj/mm/anne
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try { // TODO to be managed
				employee.setBirthDate(sdf.parse(birthDate));
				employee.setHireDate(sdf.parse(hireDate));
			} catch (ParseException e2) {
				//Réafficher le formulaire
				throw new RuntimeException("Error with date, not yet implemented");
			}
			
			try {
				myEmployeeService.createEmployee(employee);
				response.sendRedirect("http://127.0.0.1:8080/hello-webapp-0.1/EJBJPAEmployeeServlet");
			} catch (EJBException e) {
				// TODO rediriger vers une page "oups, problème veuillez réessayer"
			}
			
		}
	
		private boolean validate(String number) {
			return ((number != null) && ( number.matches("\\d+")));
		}
		
		private Throwable unrollException(Throwable exception, Class<? extends Throwable> expected) {

			  while(exception != null && exception != exception.getCause()){
			    if(expected.isInstance(exception)){
			      return exception;
			    }
			    exception = exception.getCause();
			  }
			  return null;
			}
		
		
		
		

}
