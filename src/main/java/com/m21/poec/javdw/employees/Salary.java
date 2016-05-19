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
@Table(name="salaries")
@IdClass(SalaryPkIdClass.class)

public class Salary implements Comparable<Salary>{

	/*@EmbeddedId
	private SalaryPkEmbedded salaryPK;*/
	@Id
	@Column(name="emp_no")
	private Integer empNo;
	
	@Id
	@Temporal(TemporalType.DATE)
	@Column(name="from_date")
	private Date fromDate;
	
	@Column(name="salary")
	private Integer salary;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name="to_date")
	private Date toDate;

	@ManyToOne
	@JoinColumn(name="emp_no", insertable=false, updatable=false)
	private EmployeeJPA employee;
	
	
	// Getters and Setters
	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {

		this.toDate = toDate;
	}
	
	
	public Integer getEmpNo() {
		return empNo;
	}
	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	@Override
	public String toString() {
		return "Salary [empNo=" + empNo + ", fromDate=" + fromDate + ", salary=" + salary + ", toDate=" + toDate
				+ ", employee=" + employee.getId() + "]";
	}
	
	@Override
	public int compareTo(Salary d) {
		return d.getFromDate().compareTo(this.fromDate);
	}
	
	// Methods
}


