<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FileManager</title>
</head>
<body>
	 <!-- ${files} Permet d'accéder aux attributs de la requête (transmis par la servlet)  -->
	
	<form action="http://127.0.0.1:8080/hello-webapp-0.1/Upload" method="POST" enctype="multipart/form-data">
		<input type="file" name="file">
		<input type="submit" value="uplaod">
	</form>
	<p>${ flash }</p>
	<table>
		<thead>
			<tr>
				<td> File </td>
			</tr>
		</thead>
		<tbody >
			<c:forEach items="${ files }" var="f">
			<tr>
				<td><a href="http://127.0.0.1:8080/hello-webapp-0.1/Download?file=${ f.fileName }">${ f.fileName }</a></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>