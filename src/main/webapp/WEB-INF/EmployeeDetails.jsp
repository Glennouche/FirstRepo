<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EmployeeManager</title>
</head>
<body>
	 <!-- ${files} Permet d'accéder aux attributs de la requête (transmis par la servlet)  -->
	<table>
		<thead>
			<tr>
				<td> Employees Details </td>
			</tr>
		</thead>
		<tbody >
			<c:forEach items="${ employees }" var="e">
			<tr>
				<td> Employee_ID [${e.getNumber()}]  Name:[${e.getFirst_name()}].[${e.getLast_name()}] Sex:[${e.getSex()}] Function:[${e.getTitle()}] Salary:[${e.getSalary()}€] Hire_Date:[${e.getHire_date()}] Department:[${e.getDepartement()}] </td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="http://127.0.0.1:8080/hello-webapp-0.1/employees"> Return </a>
	
	
</body>
</html>