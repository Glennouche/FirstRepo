package ejb.hello;

import javax.ejb.Stateful;

@Stateful
public class EJBStateFull {

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
