package com.m21.poec.javdw.employees;

public class Employee {

	private int number;
	private String first_name;
	private String last_name;
	private String departement;
	private String hire_date;
	private int salary;
	private String title;
	private char sex;
	
	public Employee(int number, String first_name, String last_name) {
		this.number = number;
		this.first_name = first_name;
		this.last_name = last_name;
		
	}
	
	public Employee(int number, String first_name, String last_name,String dpt, String hire_date, String title, int salary, char sex) {
		this.number = number;
		this.first_name = first_name;
		this.last_name = last_name;
		this.departement = dpt;
		this.hire_date = hire_date;
		this.title = title;
		this.salary = salary;
		this.sex = sex;
	}
	
	
	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public String getHire_date() {
		return hire_date;
	}

	public void setHire_date(String hire_date) {
		this.hire_date = hire_date;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	
	
	
	

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	
}
