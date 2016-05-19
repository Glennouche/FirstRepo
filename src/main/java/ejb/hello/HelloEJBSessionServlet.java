package ejb.hello;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HelloEJBSessionServlet")
public class HelloEJBSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// @EJB // à éviter, uniquement si pas CDI
	@Inject
	private HelloEJBSessionStateless helloServices;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.getWriter().append(helloServices.sayHello());
	}

}
