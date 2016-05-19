package com.m21.poec.javdw.employees;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="dept_emp")
@IdClass(DeptAssociationId.class)
public class DeptAssociation implements Comparable<DeptAssociation>{

	
	 @Id
	 @Column(name="emp_no")
	 private Integer empNo;
	 
	 @Id
	 @Column(name="dept_no")
	 private String deptNo;
	  
	 @Temporal(TemporalType.DATE)
	 @Column(name="from_date")
	 private Date fromDate;
	  
	 @Temporal(TemporalType.DATE)
	 @Column(name="to_date")
	 private Date toDate;
	  
	 @ManyToOne
	  //@PrimaryKeyJoinColumn(name="emp_no", referencedColumnName="emp_no") // Ne pas mettre @Id si on veut utiliser PrimaryJoinColumn
	  @JoinColumn(name = "emp_no", updatable = false, insertable = false, referencedColumnName = "emp_no")
	  /* if this JPA model doesn't create a table for the "PROJ_EMP" entity,
	  *  please comment out the @PrimaryKeyJoinColumn, and use the ff:
	  *  @JoinColumn(name = "employeeId", updatable = false, insertable = false)
	  * or @JoinColumn(name = "employeeId", updatable = false, insertable = false, referencedColumnName = "id")
	  */
	  private EmployeeJPA employee;
	  @ManyToOne
	  @JoinColumn(name = "dept_no", updatable = false, insertable = false, referencedColumnName = "dept_no")
	  //@PrimaryKeyJoinColumn(name="dept_no", referencedColumnName="dept_no") // Ne pas mettre @Id si on veut utiliser PrimaryJoinColumn
	  /* the same goes here:
	  *  if this JPA model doesn't create a table for the "PROJ_EMP" entity,
	  *  please comment out the @PrimaryKeyJoinColumn, and use the ff:
	  *  @JoinColumn(name = "projectId", updatable = false, insertable = false)
	  * or @JoinColumn(name = "projectId", updatable = false, insertable = false, referencedColumnName = "id")
	  */
	  private Departments departments;
	  
	public Integer getEmpNo() {
		return empNo;
	}
	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}
	public String getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public EmployeeJPA getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeJPA employee) {
		this.employee = employee;
	}
	public Departments getDept() {
		return departments;
	}
	public void setDept(Departments dept) {
		this.departments = dept;
	}
	@Override
	public String toString() {
		return "DeptAssociation [empNo=" + empNo + ", deptNo=" + deptNo + ", fromDate=" + fromDate + ", toDate="
				+ toDate + ", employee=" + employee.getId() + ", departments=" + departments.getDeptName() + "]";
	}
	
	@Override
	public int compareTo(DeptAssociation d) {
		return d.getFromDate().compareTo(this.fromDate);
	}
	
	
	  
	  
}
