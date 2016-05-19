package jms;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Message-Driven Bean implementation class for: JMSListenerMDB
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "java:/jms/queue/MyQueue"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "java:/jms/queue/MyQueue")
public class JMSListenerMDB implements MessageListener {

	private static final Logger LOGGER = Logger.getLogger(JMSListenerMDB.class.getName());
    /**
     * Default constructor. 
     */
    public JMSListenerMDB() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	if(message instanceof TextMessage) {
    		TextMessage textmessage = (TextMessage) message;
    		try {
    			
    		
    		LOGGER.info(String.format("Message in EJBDM : %s", textmessage.getText()));
    		} catch (JMSException e) {
    			LOGGER.info("oups....");
    		}
    	}
    		
    	
        // TODO Auto-generated method stub
        
    }

}
