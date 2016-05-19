package jaxRS;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Logger;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.m21.poec.javdw.employees.EmployeeJPA;
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class ListEmployeeJSONMessageBodyWriter implements MessageBodyWriter<List<EmployeeJPA>>{

	private static final Logger LOGGER = Logger.getLogger(ListEmployeeJSONMessageBodyWriter.class.getName());
	
	@Override
	public long getSize(List<EmployeeJPA> arg0, Class<?> arg1, Type arg2, Annotation[] arg3, MediaType arg4) {
		// TODO Auto-generated method stub
		return arg0.size();
	}

	@Override
	public boolean isWriteable(Class<?> arg0, Type arg1, Annotation[] arg2, MediaType arg3) {
		// TODO Auto-generated method stub
		// Dans arg0 on perds l'info du type contenu dans la liste

		 return List.class.isAssignableFrom(arg0)
			        && (arg1 instanceof ParameterizedType)
			        && (((ParameterizedType) arg1).getActualTypeArguments().length == 1)
			        && (((ParameterizedType) arg1).getActualTypeArguments()[0].equals(EmployeeJPA.class));
	}

	@Override
	public void writeTo(List<EmployeeJPA> arg0, Class<?> arg1, Type arg2, Annotation[] arg3, MediaType arg4,
			MultivaluedMap<String, Object> arg5, OutputStream arg6) throws IOException, WebApplicationException {
		
		
		JsonGenerator generator = Json.createGenerator(arg6);
	    generator.writeStartArray();
	   
		for (EmployeeJPA t : arg0) {
			generator.writeStartObject();
			 generator.write("id",  t.getId());
			 generator.write("firstName", t.getFirstName());
			 generator.write("lastName", t.getLastName());
			 generator.write("gender", t.getGender().toString());
			 generator.writeEnd();
		}
		generator.writeEnd();
	    generator.flush();
	}
	
}
