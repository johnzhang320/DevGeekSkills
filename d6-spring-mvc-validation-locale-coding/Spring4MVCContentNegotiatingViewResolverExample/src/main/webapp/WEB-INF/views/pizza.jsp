<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Pizza JSP View</title>
</head>
<body>
	<table border="1">
		<tr>
		<td>NAME</td>
		<td>Flavor</td>
		<td>Toppings</td>
		</tr>
		<tr>
			<td>${pizza.name}</td>
			<td>${pizza.flavor}</td>
			<td>
				<c:forEach var="item" items="${pizza.toppings}">
					<c:out value="${item}"/>&nbsp; 
				</c:forEach>
			</td>
		</tr>
	</table>
</body>
</html>