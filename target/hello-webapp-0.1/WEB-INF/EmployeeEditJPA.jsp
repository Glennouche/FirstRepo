<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EmployeeUpdate</title>
</head>
<body>
	<form method="POST">
	
		<label for="id">Id</label>
		<input id="id" type="text" name="id" value=${ employee.id }>
		
		<label for="firstName">First Name</label>
		<input id="firstName" type="text" name="firstName" value=${ employee.firstName }> 
		
		<label for="lastName">First Name</label>
		<input id="lastName" type="text" name="lastName" value=${ employee.lastName }> 
		
		<label for="gender">Gender</label>
		<select id="gender" name="gender">
			<option value="M" ${ employee.gender == 'M' ? 'selected' : '' } >M</option>
			<option value="F" ${ employee.gender == 'F' ? 'selected' : '' } >F</option>
		</select>
		
		
		
		<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
				
		<label for="birthDate">BirthDate</label>
		<fmt:formatDate value="${ employee.birthDate }" type="date" pattern="dd/MM/yyyy" var="bithFormattedDate" />
		<input id="birthDate" type="text" name="birthDate" value=${ bithFormattedDate } > 
		
		<label for="hireDate">HireDate</label>
		<fmt:formatDate value="${ employee.hireDate }" type="date" pattern="dd/MM/yyyy" var="hireFormattedDate" />
		<input id="hireDate" type="text" name="hireDate" value=${ hireFormattedDate } > 
		
	
	<input type="submit" value="Edit Employee">
	</form>
	
	
	<a href="http://127.0.0.1:8080/hello-webapp-0.1/JPAEmployeeServlet"> Return </a>
</body>
</html>