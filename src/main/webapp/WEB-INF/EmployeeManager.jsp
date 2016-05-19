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
				<td> Employees </td>
			</tr>
		</thead>
		<tbody >
			<c:forEach items="${ employees }" var="e">
			<tr>
				<td><a href="http://127.0.0.1:8080/hello-webapp-0.1/EmpDetailServlet?number=${ e.getNumber() }">${ e.getNumber() }</a></td> 
				<td> ${ e.getFirst_name() } </td>
				<td> ${ e.getLast_name() } </td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	
</body>
</html>