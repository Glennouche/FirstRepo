package ejb.hello;

import javax.ejb.Singleton;

@Singleton
public class EJBSingleton {

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
