package jaxRS;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.m21.poec.javdw.employees.EmployeeJPA;
import ejb.employee.EmployeeService;


@Path("employees")
public class EmployeesRessource {
	
	// JAXB => Mapping Objet / XML
	// JSONP => Object <=> JSON
	
	@Inject
	private EmployeeService employeesService;
	
//	@GET
//	@Produces(MediaType.APPLICATION_JSON) //"application/json"
//	public String getEmployees() {
//		List<EmployeeJPA> employees = employeesService.findAll(0, 10);
//		StringBuilder sb = new StringBuilder();
//		sb.append("[");
//		for (EmployeeJPA employeeJPA : employees) {
//			sb.append(employeeToJSONString(employeeJPA));
//			sb.append(",");
//		}
//		sb.deleteCharAt(sb.length()-1);
//		sb.append("]");
//		return sb.toString();
//	}
	
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	@Path("/{id}")
//	public String getEmployee(@PathParam("id") Integer id) {
//		EmployeeJPA employee = employeesService.findWithSalaryAndDepartment(String.valueOf(id));
//
//		if(employee == null) {
//			throw new NotFoundException();
//		}
//
//		return employeeToJSONString(employee);
//	}
//
//	private String employeeToJSONString(EmployeeJPA employee) {
//		StringBuilder sb = new StringBuilder();
//		sb.append("{");
//		sb.append(String.format("\"%s\":%d", "id", employee.getId()));
//		sb.append(",");
//		sb.append(String.format("\"%s\":\"%s\"", "firstName", employee.getFirstName()));
//		sb.append(",");
//		sb.append(String.format("\"%s\":\"%s\"", "lastName", employee.getLastName()));
//		sb.append("}");
//		return sb.toString();
//	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON) //"application/json"
	public List<EmployeeJPA> getEmployees() {
		return employeesService.findAll(0, 10);
	}
	/*	
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
		
		for (EmployeeJPA employeeJPA : employees) {
			jsonArrayBuilder.add(employeeToJSONObjectBuilder(employeeJPA));
		}

		return jsonArrayBuilder.build();
	}
	
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	@Path("/{id}")
//	public JsonObject getEmployee(@PathParam("id") Integer id) {
//		EmployeeJPA employee = employeesService.findWithSalaryAndDepartment(String.valueOf(id));
//
//		if(employee == null) {
//			throw new NotFoundException();
//		}
//
//		return employeeToJSONObjectBuilder(employee).build();
//	}
	
	/*private JsonObjectBuilder employeeToJSONObjectBuilder(EmployeeJPA employee) {
		Objects.requireNonNull(employee);
		
		JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
		jsonObjectBuilder.add("id", employee.getId());
		jsonObjectBuilder.add("firstName", employee.getFirstName());
		jsonObjectBuilder.add("lastName", employee.getLastName());
		jsonObjectBuilder.add("gender", employee.getGender().toString());
		return jsonObjectBuilder;
	}*/
	
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public EmployeeJPA getEmployee(@PathParam("id") Integer id) {
		return  (employeesService.findEmployeeDetails(String.valueOf(id)));
	}
}
