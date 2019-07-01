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
   <title>Assign Roles to User</title>
<script type="text/javascript">
	function findUsername() {
		
		var form = document.getElementById("rolesForm");
 		form.action="/spring-security-enhancement/safebox/find_roles.html";
		form.submit();
		
	}
	
</script>

</head>
<body>
<div class="container">
   
    <form:form  method="POST" id="rolesForm" name="rolesForm" modelAttribute="rolesForm" action="/spring-security-enhancement/safebox/assign_roles.html" cssClass="form-signin">
     <h2 class="form-heading">Assign Roles</h2>	
     
      <div class="form-group"> 
        
         <table style="border:hidden">
	         <tr><td>
		     	   <form:input path="username" styleId="username" cssClass="form-control" placeholder="Username" />
		     	   <form:errors path="username" cssClass="error" />
		     	 </td><td>
		     	 <button id="findUser" class="btn btn-info btn-lg" onClick="javascript:findUsername();">Find</button>
	     	 </td>
	     	 </tr>
     	 </table>
         <label id="usernameMsg" class="text-center text-danger text-light">${message}</label>        		  
   	</div>
   		<div class="form-group"> 
   		  
     	 		<!--<form:checkboxes items="${roleList}" path="myRoles" ccClass="myCheckbox" />
		 		<form:errors path="myRoles" cssClass="error" /><br/>-->
		 	<label class="text-left text-info">Select Roles:</label>
		 	<div class="form-check">	
		 		
		 	   <c:forEach var="entry" items="${roleList}">
			   <c:if test="${entry.value=='CHECKED'}">
			   		<input type="checkbox" name="myRoles" class="custom-control-input" value="${entry.key}" checked>
	 		   </c:if>  
			   <c:if test="${entry.value=='UNCHECKED'}">
			   		<input type="checkbox" name="myRoles" class="custom-control-input" value="${entry.key}" >
			   </c:if> 
			     <label class="custom-control-label" for="${entry.key}">${entry.key}</label><br/>
			    
			</c:forEach>
	 	</div>
		 	 
		</div> 		
		 <div class="form-group"> 
     <!--      <input type="submit" class="btn btn-lg btn-primary" value="Submit" id="submitform" > -->
           <button class="btn btn-lg btn-primary " type="submit">Submit</button> 
          
	 	<h4 class="text-left"><a href="${contextPath}/home"><b>Home Page</b> </a></h4>
       </div>  
      </form:form>
  </div>
</body>
</html>