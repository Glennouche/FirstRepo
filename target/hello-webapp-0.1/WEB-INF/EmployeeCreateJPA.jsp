<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EmployeeCreation</title>
</head>
<body>
	<form method="POST">
		<label for="id">Id</label>
		<input id="id" type="text" name="id">
		
		<label for="firstName">First Name</label>
		<input id="firstName" type="text" name="firstName"> 
		
		<label for="lastName">First Name</label>
		<input id="lastName" type="text" name="lastName"> 
		
		<label for="gender">Gender</label>
		<select id="gender" name="gender">
			<option value="M" selected >M</option>
			<option value="F">F</option>
		</select>
		
		
		
		
		<label for="birthDate">BirthDate</label>
		<input id="birthDate" type="text" name="birthDate" > 
		
		<label for="hireDate">HireDate</label>
		<input id="hireDate" type="text" name="hireDate" > 
		
	
	<input type="submit" value="Create Employee">
	</form>
	
	
	<a href="http://127.0.0.1:8080/hello-webapp-0.1/EJBJPAEmployeeServlet"> Return </a>
</body>
</html>