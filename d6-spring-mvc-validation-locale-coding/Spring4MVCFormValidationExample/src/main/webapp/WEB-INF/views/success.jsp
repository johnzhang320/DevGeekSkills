<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Student Enrollment Detail Confirmation</title>
	<link href="<c:url value='/static/css/custom.css' />" rel="stylesheet"></link>
</head>
<body>
	<div class="success">
		Confirmation message : ${success}
		<br>
		First Name:${student.firstName}<br>
		Last Name: ${student.lastName}<br>
		Gender: ${student.sex}<br>
		Email: ${student.email}<br>
		Section: ${student.section}<br>
		Country: ${student.country}<br>
		FirstAttempt: ${student.firstAttempt}<br>
		Subjects: ${student.subjects}<br>
		
		We have also sent you a confirmation mail to your emailAddress address : ${student.email}.
	</div>
</body>
</html>