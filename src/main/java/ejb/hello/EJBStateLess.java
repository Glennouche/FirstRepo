package ejb.hello;

import javax.ejb.Stateless;

@Stateless
public class EJBStateLess {
	private Double result;
	
	public void play() {
		if(result == null) {
			result = Math.random();
		}
	}
	
	
	public Double getResult() {
		return result;
	}

}
