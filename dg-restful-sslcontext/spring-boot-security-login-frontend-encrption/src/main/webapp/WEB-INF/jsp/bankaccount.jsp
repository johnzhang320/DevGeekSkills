<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
 <c:set var="contextPath" value="${pageContext.request.contextPath}"/>

 <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
   <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
   <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
<br/> 
<div class="container">
<h2>Congratulations, you successfully sign on highly confidential page!</h2>
 <fieldset>
 
        <legend><span>Bank Account Information </span></legend>
        <table style="width:940px;">
			<tr class="row" style="border: 1px solid green; padding: 10px" >
				<td class="col-md-4 text-center" style="border: 1px solid green; padding: 10px">Name</td>
				<td class="col-md-4 text-center" style="border: 1px solid green; padding: 10px">Email</td>
				<td class="col-md-4 text-center" style="border: 1px solid green; padding: 10px">Address</td>
				<td class="col-md-4 text-center" style="border: 1px solid green; padding: 10px">Checking</td>
				<td class="col-md-4 text-center" style="border: 1px solid green; padding: 10px">Saving</td>
			</tr> 
			<c:forEach var="bankAccount" items="${bankAccount}">
			   <tr class="row" style="border: 1px solid green; padding: 10px">
				<td class="col-md-4 text-center" style="border: 1px solid green; padding: 10px">${bankAccount.name}</td>
				<td class="col-md-4 text-center" style="border: 1px solid green; padding: 10px">${bankAccount.email}</td>
				<td class="col-md-4 text-center" style="border: 1px solid green; padding: 10px">${bankAccount.address}</td>
				<td class="col-md-4 text-center" style="border: 1px solid green; padding: 10px">${bankAccount.checking}</td>
				<td class="col-md-4 text-center" style="border: 1px solid green; padding: 10px">${bankAccount.saving}</td>
				</tr>
			
			</c:forEach>
	 
   		</table>
 </fieldset>
 <br/> 
    <c:if test="${fullTokenString != null}">
         <label class="text-left">${fullTokenString}</label>
    </c:if>
    <c:if test="${fullTokenString == null}">
 		 <a href="${contextPath}/home" class="btn btn-primary">Home Page</a>
  	</c:if>
 
 </div>