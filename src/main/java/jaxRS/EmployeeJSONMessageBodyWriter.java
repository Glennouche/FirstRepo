package jaxRS;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.logging.Logger;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.m21.poec.javdw.employees.EmployeeJPA;


@Provider
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeJSONMessageBodyWriter implements MessageBodyWriter<EmployeeJPA> {

	private static final Logger LOGGER = Logger.getLogger(EmployeeJSONMessageBodyWriter.class.getName());
	
	@Override // Permet d'exprimer ce que l'on vas supporter
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		LOGGER.info("###### CALLED EmployeeJSONMessageBodyWriter isWriteable ######");
		return type == EmployeeJPA.class;
		// ou return type.isAssignableFrom(EmployeeJPA.class) pour traiter le type exacte et tous les sous type d'EmployeeJPA
		// API reflection
	}

	@Override
	public long getSize(EmployeeJPA t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return -1;
	}

	@Override
	public void writeTo(EmployeeJPA t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {
		
		LOGGER.info("###### CALLED EmployeeJSONMessageBodyWriter writeTo ######");
		//entityStream.write(EmployeeJPA.getbytes());
		if(t == null ) {
			throw new NotFoundException();
			
		}

		//entityStream.write(JsonObjectBuilder.build().toString().getBytes());
		/*JsonWriter writer =Json.createWriter(entityStream);
		writer.writeObject(JsonObjectBuilder.build());
		writer.close();*/
		
		//Best method : 
		JsonGenerator generator = Json.createGenerator(entityStream);
	    generator.writeStartObject()
	    .write("id",  t.getId())
	    .write("firstName", t.getFirstName())
	    .write("lastName", t.getLastName())
	    .write("gender", t.getGender().toString())
	    .write("birthDate", t.getBirthDate().toString())
	    .write("hireDate", t.getHireDate().toString())
	    .writeEnd();
	    generator.flush();
	}

}
