package com.m21.poec.javdw.employees;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class EmpDetailServlet
 */
@WebServlet("/EmpDetailServlet")
public class EmpDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup="java:jboss/datasources/EmployeesDS") // @Resource est forcément une dépendance JNDI
	private DataSource employeesDS; // La Data Source est crée par le servlet container de façon thread safe !!  
	private static final Logger LOGGER = Logger.getLogger(EmpDetailServlet.class.getName());
	
	private static final String EMPLOYYE_DETAILS_BY_NUMBER = "SELECT EP.first_name, EP.last_name, DPT.dept_name, EP.hire_date, TIT.title, SA.salary, EP.gender FROM "
			+ "employees EP INNER JOIN salaries SA ON EP.emp_no = SA.emp_no "
			+ "INNER JOIN titles TIT ON EP.emp_no = TIT.emp_no "
			+ "INNER JOIN dept_emp DPE ON EP.emp_no = DPE.emp_no "
			+ "INNER JOIN departments DPT ON DPE.dept_no = DPT.dept_no "
			+ "WHERE EP.emp_no = ? LIMIT 1" 
			;
	
	private static List<Employee> effectif = new ArrayList<>();
	private static Employee resultEmp;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String number = request.getQueryString();
		
		if(!validate(number)){
			response.sendError(404);
			return;
		}
		
		try(Connection con = employeesDS.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(EMPLOYYE_DETAILS_BY_NUMBER);
			stmt.setString(1, number);
			ResultSet rs = stmt.executeQuery();
			
			if(! rs.next()) {
				response.sendError(404);
				return;
			}	
			
			resultEmp = new Employee(Integer.parseInt(number),rs.getString("EP.first_name"), rs.getString("EP.last_name"), rs.getString("DPT.dept_name"), rs.getString("EP.hire_date"), rs.getString("TIT.title"),rs.getInt("SA.salary"),rs.getString("EP.gender").charAt(0));
			effectif.add(resultEmp);	
			
		}catch(SQLException ex) {
			response.sendError(500,"Veuillez ré-essayer dans 5 min");
			return;
		}	
		renderResponse(request, response);
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
	
	private static void renderResponse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.setAttribute("employees", effectif);
		request.getRequestDispatcher("/WEB-INF/EmployeeDetails.jsp").forward(request, response);
		
	}

}
