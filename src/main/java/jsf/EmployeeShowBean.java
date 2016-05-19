package jsf;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.m21.poec.javdw.employees.EmployeeJPA;

import ejb.employee.EmployeeService;
import jaxRS.EmployeeJSONMessageBodyWriter;

@Named
@RequestScoped
public class EmployeeShowBean {

	private Integer id;
	private static final Logger LOGGER = Logger.getLogger(EmployeeShowBean.class.getName());
	
	private EmployeeJPA employee;

	@Inject
	private EmployeeService employeesService;
	
	
	
	public Integer getId() {
		LOGGER.info(String.format("getId : Current Phase id : %s", FacesContext.getCurrentInstance().getCurrentPhaseId()));
		return id;
	}
	
	// Call during UPDATE_MODEL_VALUE PHASE
	public void setId(Integer id) {
		LOGGER.info(String.format("setId : Current Phase id : %s", FacesContext.getCurrentInstance().getCurrentPhaseId()));
		this.id = id;
		this.employee = employeesService.findEmployeeDetails(String.valueOf(id));
	}
	
	// Call during INVOQUE_APPLICATION PHASE
	public void loadEmployee() {
		LOGGER.info(String.format("loadEmployee : Current Phase id : %s", FacesContext.getCurrentInstance().getCurrentPhaseId()));
	}
	
	// Call during RENDER_RESPONSE PHASE
	public EmployeeJPA getEmployee(){
		LOGGER.info(String.format("getEmployee : Current Phase id : %s", FacesContext.getCurrentInstance().getCurrentPhaseId()));
		//return employeesService.findEmployeeDetails(String.valueOf(id));
		return employee;
	}

	
}
