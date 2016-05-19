package ejb.hello;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class HelloEJBSessionStateless
 */
@Stateless
public class HelloEJBSessionStateless {

	public String sayHello() {
		return "Hello You <3";
	}
}
