<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
 <style>
  
fieldset table tr td {
     border:1px;
   }
 </style> 
 
 
 
<div > 
      
	<div class="module-title">
	Welcome to loans-agent.com
	<input type="button" value="Help" class="helpbutton" onclick="javascript:popUpFixedName('<%=request.getContextPath()%>/helpAgentSingup.html','750','600');">  
	</div>
	<fieldset >  
		<legend><span class="AccountCreateTableHeader"> <spring:message code="title.signup.success"/></span> </legend>
		<br>
		<table>   
			<tr><td><spring:message code="label.full.name"/></td><td>${agentTable.fullName}</td></tr>
			<tr><td> <spring:message code="label.username"/></td><td>${agentTable.emailAddress}</td></tr>
			<tr><td><spring:message code="label.ip.address"/></td><td>${agentTable.ipAddress}</td></tr>
			<tr><td><spring:message code="label.ip.address.range"/></td><td>${agentTable.ipAddressRange}</td></tr>
			
				
			<tr><td><spring:message code="label.company.name"/></td><td>${agentTable.companyName}</td></tr>
			<tr><td><spring:message code="label.description"/></td><td>${agentTable.description}</td></tr>
			<tr><td><spring:message code="label.work.phone"/></td><td>${agentTable.workPhone}</td></tr>
			<tr><td><spring:message code="label.cell.phone"/></td><td>${agentTable.cellPhone}</td></tr>
			<tr><td><spring:message code="label.address.street"/></td><td>${agentTable.address}</td></tr>
			<tr><td><spring:message code="label.city"/></td><td>${agentTable.city}</td></tr>
			<tr><td><spring:message code="label.state"/></td><td>${agentTable.state}</td></tr>
			<tr><td><spring:message code="label.zip.code"/></td><td>${agentTable.zipCode}</td></tr>                        
		</table>	
	</fieldset>
  		 
 </div>