<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
   <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
   <title>Assign Permissions to Roles</title>


</head>
<body>
<div class="container">
  
    <form:form  method="POST" id="matrixForm" name="matrixForm" modelAttribute="matrixForm" action="/safebox/assign_permissions.html" >
     <h2 class="form-heading">Assign Permissions to Roles</h2>	
    
    		  
		 	<label class="text-left text-info">Select Permissions for Roles:</label>
		  
		 	    <table style="width:700px;">
				 	  	<c:forEach var="row" items="${permMatrix}">
					 	  	<tr class="row" style="border: 1px solid green; padding: 10px" >
					 	       	<c:forEach var="col" items="${row}">
					 	       		<td class="col-md-4 text-center" style="border: 1px solid green; padding: 10px; ">
						 	            <c:choose>
										  <c:when test="${col=='CHECKED'}">
										     	<input type="checkbox" name="matrix" class="custom-control-input" value="CHECKED" checked>
										  </c:when>
										  <c:when test="${col=='UNCHECKED'}">
										    	<input type="checkbox" name="matrix" class="custom-control-input" value="UNCHECKED" >
										  </c:when>
										  <c:otherwise>
										  		<!--<input type="input" name="matrix" class="custom-control-input" value="${col}" disabled>-->
										    	 <label class="custom-control-label" for="${col}">${col}</label>
										  </c:otherwise>
										</c:choose>  
									</td>
			 			  		</c:forEach> 
			 			  		</tr>
						</c:forEach>
				</table>
	 	 
		 	 
		 
		
     <!--      <input type="submit" class="btn btn-lg btn-primary" value="Submit" id="submitform" > -->
           <button class="btn btn-lg btn-primary " type="submit">Submit</button> 
          
	 	<h4 class="text-left"><a href="${contextPath}/home"><b>Home Page</b> </a></h4>
     
       
      </form:form>
 
  </div>
</body>
</html>