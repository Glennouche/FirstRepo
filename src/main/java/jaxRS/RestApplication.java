package jaxRS;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;

@ApplicationPath("/rest")
public class RestApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> ressources = new HashSet<>();

		ressources.add(EmployeesRessource.class);

		return ressources;
	}

	@Override
	public Set<Object> getSingletons() {
		Set<Object> singletons = new LinkedHashSet<Object>();
		singletons = new LinkedHashSet<Object>();
		
		CorsFilter corsFilter = new CorsFilter();
		corsFilter.getAllowedOrigins().add("*");
		singletons.add(corsFilter);
		
		singletons.add(new EmployeeJSONMessageBodyWriter());
		singletons.add(new ListEmployeeJSONMessageBodyWriter());
		return singletons;
	}

}