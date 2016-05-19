package com.m21.poec.javdw.employees;

import java.io.IOException;
import java.sql.Connection;
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
 * Servlet implementation class EmployeesServlet
 */
@WebServlet("/employees")
public class EmployeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String FIND_FIRST10_EMPLOYEES = "SELECT * FROM employees LIMIT 10";
	
	@Resource(lookup="java:jboss/datasources/EmployeesDS") // @Resource est forcément une dépendance JNDI
	private DataSource employeesDS; // La Data Source est crée par le servlet container de façon thread safe !! 
	private static final Logger LOGGER = Logger.getLogger(EmployeesServlet.class.getName());
	private static List<Employee> effectif = new ArrayList<>();
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LOGGER.info(String.format("DataSource : %s",employeesDS));
		try(Connection con = employeesDS.getConnection()) {
			ResultSet rs = con.createStatement().executeQuery(FIND_FIRST10_EMPLOYEES);
			
			while(rs.next()) {
				LOGGER.info(String.format("%d %s %s", rs.getInt("emp_no"), rs.getString("first_name"), rs.getString("last_name")));
				effectif.add(new Employee(rs.getInt("emp_no"), rs.getString("first_name"), rs.getString("last_name")));
			}
			renderResponse(request, response);
			
		}catch(SQLException ex) {
			LOGGER.log(Level.INFO, "Problem using employeesDS", ex);
			response.sendError(500,"Veuillez ré-essayer dans 5 min");
			return;
		}
		//response.getWriter().append("Served Ratatat: ").append(request.getContextPath());
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
			request.getRequestDispatcher("/WEB-INF/EmployeeManager.jsp").forward(request, response);
			
		}

}
