package ejb.hello;

import java.io.IOException;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SteatelessStatefullWizard
 */

@WebServlet("/SteatelessStatefullWizard")

public class SteatelessStatefullWizard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger LOGGER = Logger.getLogger(SteatelessStatefullWizard.class.getName()); 
	@Inject
	EJBStateFull myStateFull;
	
	@Inject
	EJBStateLess myStateLess;
	
	@Inject
	EJBSingleton mySingleton;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SteatelessStatefullWizard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LOGGER.info("***************** STATE FULL **********************");
		LOGGER.info("StateFull GetResult() : "+ myStateFull.getResult());
		myStateFull.play();
		LOGGER.info("StateFull GetResult() : "+ myStateFull.getResult());
		myStateFull.play();
		LOGGER.info("StateFull GetResult() : "+ myStateFull.getResult());
		myStateFull.play();
		LOGGER.info("***************** STATE LESS **********************");
		LOGGER.info("StateLess GetResult() : "+ myStateLess.getResult());
		myStateLess.play();
		LOGGER.info("StateLess GetResult() : "+ myStateLess.getResult());
		myStateLess.play();
		LOGGER.info("StateLess GetResult() : " + myStateLess.getResult());
		myStateLess.play();
		
		
		LOGGER.info("***************** SINGLETON **********************");
		LOGGER.info("StateLess GetResult() : "+ mySingleton.getResult());
		mySingleton.play();
		LOGGER.info("StateLess GetResult() : "+ mySingleton.getResult());
		mySingleton.play();
		LOGGER.info("StateLess GetResult() : " + mySingleton.getResult());
		mySingleton.play();
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
