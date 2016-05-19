package ejb.hello;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Logger;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class AnalyticsSingleton {
	
	private static final Logger LOGGER  = Logger.getLogger(AnalyticsSingleton.class.getName());

	Map<String, Integer> hitsPerPage = new HashMap<>();
	
	// Pour synchroniser (multi-threading)
	@Lock(LockType.WRITE)
	public void hitPage(String pageURL) {
		hitsPerPage.put(pageURL,hitsPerPage.getOrDefault(pageURL, 0)+1);
	}
	
	@Lock(LockType.READ)
	public Integer getHisPerPage( String pageURL) {
		return hitsPerPage.getOrDefault(pageURL, 0);
	}
	
	@Lock(LockType.READ)
	public Set<Entry<String,Integer>> visitedPAges() {
			return hitsPerPage.entrySet();
	}
	
	@Schedule(second="*/30", minute="*",hour="*")
	public void buildAnalyticReport() {
		LOGGER.info("---------------- ANALYTICS REPORT -----------------");
		for(Entry<String, Integer> entry: hitsPerPage.entrySet()) {
			LOGGER.info(String.format("Page : %s, visited %d times" , entry.getKey(), entry.getValue()));
		}
	}
}
