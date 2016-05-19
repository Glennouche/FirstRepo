package com.m21.poec.javdw.employees;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class SalaryPkEmbedded implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6428996427255991454L;


	@Column(name="emp_no")
	private Integer empNo;
	

	@Temporal(TemporalType.DATE)
	@Column(name="from_date")
	private Date fromDate;


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((empNo == null) ? 0 : empNo.hashCode());
		result = prime * result + ((fromDate == null) ? 0 : fromDate.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SalaryPkEmbedded other = (SalaryPkEmbedded) obj;
		if (empNo == null) {
			if (other.empNo != null)
				return false;
		} else if (!empNo.equals(other.empNo))
			return false;
		if (fromDate == null) {
			if (other.fromDate != null)
				return false;
		} else if (!fromDate.equals(other.fromDate))
			return false;
		return true;
	}
	
	
}
