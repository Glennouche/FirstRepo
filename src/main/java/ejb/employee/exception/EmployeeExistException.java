package ejb.employee.exception;

import javax.ejb.ApplicationException;

// Par exemple
@ApplicationException(rollback=false)
public class EmployeeExistException extends RuntimeException {
	
	public EmployeeExistException(String message, Throwable cause) {
		super(message, cause);
	}
}
