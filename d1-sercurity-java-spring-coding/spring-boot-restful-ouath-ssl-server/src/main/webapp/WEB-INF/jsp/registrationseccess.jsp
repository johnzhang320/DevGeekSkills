
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

 <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
   <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
   <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
<br/> 
<div class="container">
     <h2>Welcome <label class="text-info">${userForm.username}</label></h2>
	 <fieldset>
	 
	        <legend><span>Your Roles Details </span></legend>
	       <h2 class="text-info">${message}</h2> 
	 </fieldset>
 <br/>
  <a href="${contextPath}/login" class="btn btn-primary">Log In<br/><br/>
  <a href="${contextPath}/home" class="btn btn-primary">Home Page</a>
 </div>

 