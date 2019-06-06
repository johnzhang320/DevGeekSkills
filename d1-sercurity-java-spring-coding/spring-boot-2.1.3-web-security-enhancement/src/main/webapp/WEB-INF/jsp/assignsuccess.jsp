
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

 <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
   <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
   <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
<br/> 
<div class="container">
     <h2>Successfully assigned roles to user: ${rolesForm.username}</h2>
	 <fieldset>
	 
	        <legend><span>User Roles Details </span></legend>
	       <h2 class="text-info">${rolesForm.message}</h2> 
	 </fieldset>
 <br/>

  <a href="${contextPath}/home" class="btn btn-primary">Home Page</a>
 </div>

 