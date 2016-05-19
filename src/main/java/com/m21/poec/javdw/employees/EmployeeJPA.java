package com.m21.poec.javdw.employees;

import java.util.Date;
import java.util.SortedSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

// Toutes les classes qui seront des entitées du point de vue JPA 
//	doivent être déclarées avec l'annotaiton entity
@Entity
@Table(name="employees")
@NamedQuery(name="EmployeeJPA.FIND_ALL_ORDER_BY_ID", 
			query ="SELECT e_alias FROM EmployeeJPA e_alias ORDER BY e_alias.id")

//@XmlRootElement //(name="toto") // Notation JAXB
// On peut créer une contrainte sur des classes par exemple @HireDateSupBirthDate
public class EmployeeJPA {

	public enum Gender {M,F;}
	
	//@Transient annotation qui permet d'empêcher la persistence d'un attribut
	// Utilisé par exemples pour des champs calculés
	
	@Id // Identifiant de persistance (90% des cas un lien vers la primary_key)
	// Si Id est en mode Auto Increment : @GeneratedValue(strategy=GenerationType.IDENTITY)
	@NotNull
	@Column(name="emp_no")
	//@XmlElement(name="titi")
	//@XmlAttribute
	private Integer id;
	// Il existe des parseur automatiques pour directement tranformer un String en integer
	
	@NotNull
	// Exemple : @Size(min=0, max=50)
	@Size(min=1) // Une exception sera généré par hibernate avant d'executer une requête 
	@Column(name="first_name")
	private String firstName;
	
	@NotNull
	@Size(min=1) 
	@Column(name="last_name")
	private String lastName;
	 
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name="birth_date")
	private Date birthDate;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name="hire_date")
	private Date hireDate;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	// Pour JPA/Hibernate on préfère utiliser un Set plutôt qu'une liste
	 // FetchType EAGGER pour forcer à remonter la table salary
	@OrderBy("from_date DESC")
	@OneToMany(mappedBy="employee")
	//@XmlTransient // Empeche JAXB de serialiser salary
	private SortedSet<Salary> salaries;
	
	
	@OneToMany(mappedBy="employee")
	@OrderBy("from_date DESC")
	//@XmlTransient
	private SortedSet<DeptAssociation> deptAssociations;
	
	
	
	// POJO :
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {   
		return lastName;
	}
	public void setLastName(String lastNAme) {
		this.lastName = lastNAme;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public SortedSet<Salary> getSalaries() {
		return salaries;
	}
	public void setSalaries(SortedSet<Salary> salaries) {
		this.salaries = salaries;
	}
	

	
	public SortedSet<DeptAssociation> getDepart_history() {
		return deptAssociations;
	}
	public void setDepart_history(SortedSet<DeptAssociation> depart_history) {
		this.deptAssociations = depart_history;
	}

	@Override
	public String toString() {
		return "EmployeeJPA [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate="
				+ birthDate + ", hireDate=" + hireDate + ", gender=" + gender + ", salaries=" + salaries
				+ ", depart_history=" + deptAssociations + "]";
	}
	
	
	
}
