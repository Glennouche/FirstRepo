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
	 <a href="http://127.0.0.1:8080/hello-webapp-0.1/EJBJPAEmployeeServlet/Create/">Add a new employee</a>
	<table border="1" cellpadding="5" cellspacing="5">
		<thead>
			<tr>
				<td> Employees </td>
			</tr>
		</thead>
		<tbody >
			<c:forEach items="${ employees }" var="e">
			<tr>
				<td><a href="http://127.0.0.1:8080/hello-webapp-0.1/EJBJPAEmployeeServlet/Show/${ e.getId() }">${ e.getId() }</a></td> 
				<td> ${ e.getFirstName() } </td>
				<td> ${ e.getLastName() } </td>
			</tr>
			</c:forEach>
			 <%--For displaying Previous link except for the 1st page --%>
			    <c:if test="${currentPage != 1}">
			        <td><a href="http://127.0.0.1:8080/hello-webapp-0.1/EJBJPAEmployeeServlet?page=${currentPage - 1}" method="GET">Previous</a></td>
			    </c:if>
			    <%--For displaying Next link --%>
			    <c:if test="${currentPage lt noOfPages}">
			        <td><a href="http://127.0.0.1:8080/hello-webapp-0.1/EJBJPAEmployeeServlet?page=${currentPage + 1}" method="GET">Next</a></td>
			    </c:if>
			
			 <table border="1" cellpadding="5" cellspacing="5">
		        <tr>
		            <c:forEach begin="1" end="${noOfPages}" var="i">
		                <c:choose>
		                    <c:when test="${currentPage eq i}">
		                        <td>${i}</td>
		                    </c:when>
		                    <c:otherwise>
		                        <td><a href="http://127.0.0.1:8080/hello-webapp-0.1/EJBJPAEmployeeServlet?page=${i}">${i}</a></td>
		                    </c:otherwise>
		                </c:choose>
		            </c:forEach>
		        </tr>
		    </table>
		</tbody>
	</table>	
</body>
</html>