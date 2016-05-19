package ejb.employee;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

import com.m21.poec.javdw.employees.EmployeeJPA;

import ejb.hello.AnalyticsSingleton;

/**
 * Session Bean implementation class EmployeeService
 */
@Stateless // No interface view : toutes les methos public nonj static seront visibles après l'inject
			// Accessible que dans le même container
public class EmployeeService {
	
	@Inject
	private AnalyticsSingleton analyticservice;
	
	public EmployeeService() {
		// analyticservice est null
	}
	
	@PostConstruct
	public void init() {
		// cette méthode sera appelée juste après l'initialisaiton des dépendances
		// analyticservice existe ici
	}
	
	@PreDestroy
	public void destroy() {
		
	}
	
	@PersistenceContext(unitName="EmployeesPU")
	private EntityManager em; // automitaiquement fait : joinTransaciton()
	
	// Les transactions sont configurables par annotations
	
	/*@Resource
	private UserTransaction utx;*/ //Pas besoin car chaque méthode d'un EJB Session est exécutée dans une transaction 
	
	public List<EmployeeJPA> findAll(int from, int maxResult) {
		
		TypedQuery<EmployeeJPA> query = em.createNamedQuery("EmployeeJPA.FIND_ALL_ORDER_BY_ID", EmployeeJPA.class)
				.setFirstResult(from)
				.setMaxResults(maxResult);
		
		List<EmployeeJPA> employees = query.getResultList();
		return employees;
	}
	
	public EmployeeJPA findEmployeeDetails(String empNo) {
		EmployeeJPA employee = null;
		try {
			employee = em.createQuery("SELECT e FROM EmployeeJPA e LEFT JOIN FETCH e.deptAssociations WHERE e.id = :id", EmployeeJPA.class) // On ne met pas la condition de jointure  
					.setParameter("id", Integer.parseInt(empNo))
					.getSingleResult();
			employee.getDepart_history().size(); 	// Pour forcer les fetchs lazys
			employee.getSalaries().size();			// Pour forcer les fetchs lazys
			
		} catch( NoResultException | NonUniqueResultException e ) {
			// TODO Logger l'exception
		}
		return employee;
	}
	
	public void updateEmployee(EmployeeJPA newEmployee) {
		EmployeeJPA origEmployee = findEmployeeDetails(newEmployee.getId().toString());
		//origEmployee.setId(newEmployee.getId());
		origEmployee.setFirstName(newEmployee.getFirstName());
		origEmployee.setLastName(newEmployee.getLastName());
		origEmployee.setBirthDate(newEmployee.getBirthDate());
	}
	
	public void deleteEmployee(String empNo) {
		EmployeeJPA empToDelete = findEmployeeDetails(empNo);
		if(empToDelete == null) {
			//TODO Manage error
		}
		try {
			em.remove(empToDelete);
		} catch (IllegalArgumentException | TransactionRequiredException e) {
			// TODO Manage error
		}
	}
	
	
	/*
	 * Create an Employee entry in SQL DB
	 *  
	 *    
	 *  @Throws EntityExistException or PersitenceException if employee already exist
	 *  
	 */
	//@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) //implicite
	public void createEmployee(EmployeeJPA newEmployee) {
		/*if(newEmployee == null) {
			throw new NullPointerException();
		}*/
		Long. 
		em.persist(newEmployee);
	}
	
	public long getEmployeeTotalNumber() {
		long count=0;
		try {
			count = em.createNamedQuery("SELECT COUNT(e) FROM EmployeeJPA e", Long.class).getSingleResult();
			
		} catch( NoResultException | NonUniqueResultException e ) {
			// TODO Logger l'exception
		}
		return count;
	}

	
}
