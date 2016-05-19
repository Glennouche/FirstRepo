package jms;

import java.io.IOException;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.Queue;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class JMSSendMessageServlet
 */
@WebServlet("/JMSSendMessageServlet")
public class JMSSendMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger LOGGER = Logger.getLogger(JMSSendMessageServlet.class.getName());

	@Resource(lookup="java:/jms/queue/MyQueue")
	private Queue queue;
	
	@Inject // On est dans JEE7 et dans JEE7 on a JMS2.0
	private JMSContext context;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		JMSProducer producer = context.createProducer();
		Message message = context.createTextMessage("Je viens de traverser une queue mon gars !!!!");
		producer.send(queue, message);
		LOGGER.info(String.format("Message : %s", message));
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
