package jsf;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.m21.poec.javdw.employees.EmployeeJPA;

import ejb.employee.EmployeeService;

@Named // Par default = employeesListBean (nom de la classe avec premiere lettre en minuscule)
@RequestScoped
//@SessionScoped
public class EmployeesListBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	

	private static final Logger LOGGER = Logger.getLogger(EmployeesListBean.class.getName());
	private Long pageNumber;
	private Integer EmployeePerPage = 10;
	private Long lastPage;


	@Inject
	private EmployeeService employeesService;
	
	private List<EmployeeJPA> employees;
	
	@PostConstruct
	// Post construct = juste après la résolution des dépendances
	private void init() {
		pageNumber = 1;
		employees = employeesService.findAll(pageNumber, EmployeePerPage);
		long nbOfEmployeeInDb = employeesService.getEmployeeTotalNumber();
		lastPage = ((nbOfEmployeeInDb) / EmployeePerPage) + 1;
	}
	
	
	public List<EmployeeJPA> getEmployees() {
		LOGGER.info(String.format("getEmployees : Current Phase id : %s", FacesContext.getCurrentInstance().getCurrentPhaseId()));
		LOGGER.info(String.format("MinBound : %s", pageNumber));
		return employees;
	}


	public Integer getPageNumber() {
		LOGGER.info(String.format("getPageNumber : Current Phase id : %s", FacesContext.getCurrentInstance().getCurrentPhaseId()));
		return pageNumber;
	}


	public void setPageNumber(Long pageNumber) {
		LOGGER.info(String.format(" SEEEEEEEEEEEEEEEEEEEEEEEEEET PageNumber : %s", pageNumber));
		this.pageNumber = pageNumber;
		
		// TODO be carefull with this wild cast
		employees = employeesService.findAll(pageNumber*10, EmployeePerPage);
	}
	
	public boolean isLastPage() {
		return pageNumber == lastPage;	
	}
	
	
}
