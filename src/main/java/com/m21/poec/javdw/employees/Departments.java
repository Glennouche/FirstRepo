package com.m21.poec.javdw.employees;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="departments")
public class Departments {
	@Id
	@Column(name="dept_no")
	private String deptNo;
	
	@Column(name="dept_name")
	private String deptName;
	
	@OneToMany(mappedBy="departments")
	private Set<DeptAssociation> deptAssociations;
	
	 
	  // Add an employee to the project.
	  // Create an association object for the relationship and set its data.
	  public void addEmployee(EmployeeJPA employee, Date fromDate, Date toDate) {
	    DeptAssociation association = new DeptAssociation();
	    association.setEmployee(employee);
	    association.setDept(this);
	    association.setEmpNo(employee.getId());
	    association.setDeptNo(this.getDeptNo());
	    association.setFromDate(fromDate);
	    association.setToDate(toDate);
	    this.deptAssociations.add(association);
	    // Also add the association object to the employee.
	    employee.getDepart_history().add(association);
	  }


	public String getDeptNo() {
		return deptNo;
	}


	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}


	public String getDeptName() {
		return deptName;
	}


	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	public Set<DeptAssociation> getEmployees() {
		return deptAssociations;
	}


	public void setEmployees(Set<DeptAssociation> employees) {
		this.deptAssociations = employees;
	}
	  
	  
}
