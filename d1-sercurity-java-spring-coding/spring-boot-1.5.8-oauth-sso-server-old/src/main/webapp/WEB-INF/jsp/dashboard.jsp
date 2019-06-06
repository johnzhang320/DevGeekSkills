
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

 <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
   <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
   <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
<br/> 
<div class="container">
     <h2>Congratulations, you successfully sign on!</h2>
	 <fieldset>
	 
	        <legend><span>User Details </span></legend>
	        <table style="width:940px;">
				<tr class="row" style="border: 1px solid green; padding: 10px" >
					<td class="col-md-4 text-center" style="border: 1px solid green; padding: 10px">Customer Name</td>
					<td class="col-md-4 text-center" style="border: 1px solid green; padding: 10px">Email</td>
					<td class="col-md-4 text-center" style="border: 1px solid green; padding: 10px">Address</td>
					
				</tr> 
				<c:forEach var="user" items="${users}">
				   <tr class="row" style="border: 1px solid green; padding: 10px">
					<td class="col-md-4 text-center" style="border: 1px solid green; padding: 10px">${user.name}</td>
					<td class="col-md-4 text-center" style="border: 1px solid green; padding: 10px">${user.email}</td>
					<td class="col-md-4 text-center" style="border: 1px solid green; padding: 10px">${user.address}</td>
					</tr>
				
				</c:forEach>
		 
	   		</table>
	 </fieldset>
 <br/>
  <a href="${contextPath}/home" class="btn btn-primary">Home Page</a>
 </div>

 