package com.m21.poec.javdw.employees;

import java.io.Serializable;
import java.util.Date;


public class SalaryPkIdClass implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8671512735517398599L;
	private Integer empNo;
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
		SalaryPkIdClass other = (SalaryPkIdClass) obj;
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
