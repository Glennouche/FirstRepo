<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EmployeeManager</title>
</head>
<body>
	 <!-- ${files} Permet d'accéder aux attributs de la requête (transmis par la servlet)  -->
	<table>
		<h2> Employee Details </h2>
		<thead>
			<tr>
				<th> Id </th>
				<th> FirstName/LastNAme </th>
				<th> Gender </th>
				<th> BirthDate </th>
				<th> HireDate </th>
			</tr>
		</thead>
		<tbody >
			<tr>
				<td> [${employee.id}] </td>
				<td> [${employee.firstName}].[${employee.lastName}] </td>
				<td> [${employee.gender}] </td>
				<td> [${employee.birthDate}] </td>
				<td> [${employee.hireDate} ] </td>
				<td>
					<a href="http://127.0.0.1:8080/hello-webapp-0.1/EJBJPAEmployeeServlet/Edit/${employee.getId() }">Edit</a>
					<a href="http://127.0.0.1:8080/hello-webapp-0.1/EJBJPAEmployeeServlet/Delete/${employee.getId() }">Delete</a>
				</td>
			</tr>
		</tbody>
		<thead>
			<tr>
				<th> Salary (€) </th>
				<th> FromDate </th>
				<th> ToDate </th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${ employee.salaries }" var="s">
			<tr> 
				<fmt:formatDate value="${  s.getFromDate() }" type="date" pattern="dd/MM/yyyy" var="SfromFormattedDate" />
				<fmt:formatDate value="${ s.getToDate() }" type="date" pattern="dd/MM/yyyy" var="StoFormattedDate" />
				<td> ${ s.getSalary() } </td>
				<td> ${ SfromFormattedDate } </td>
				<td> ${ StoFormattedDate } </td>
			</tr>
			</c:forEach>
		</tbody>
		<thead>
			<tr>
				<th> DEP_NO </th>
				<th> FromDate </th>
				<th> ToDate </th>
				<th> DEP_NAME </th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${ employee.getDepart_history() }" var="d">
			<tr>
				<td> ${ d.getDeptNo() } </td> 
				<fmt:formatDate value="${ d.getFromDate() }" type="date" pattern="dd/MM/yyyy" var="fromFormattedDate" />
				<fmt:formatDate value="${ d.getToDate() }" type="date" pattern="dd/MM/yyyy" var="toFormattedDate" />
				<td> ${ fromFormattedDate } </td>
				<td> ${ toFormattedDate } </td>
				<td> ${ d.getDept().getDeptName() }</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="http://127.0.0.1:8080/hello-webapp-0.1/EJBJPAEmployeeServlet"> Return </a>
	
	
</body>
</html>