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
                        <tr><td>  <spring:message code="label.username"/></td><td>${agentForm.emailAddress}</td></tr>
                        <tr><td><spring:message code="label.firstname"/></td><td>${agentForm.firstName}</td></tr>
                        <tr><td><spring:message code="label.lastname"/></td><td>${agentForm.lastName}</td></tr>
                        <tr><td><spring:message code="label.company.name"/></td><td>${agentForm.companyName}</td></tr>
                        <tr><td><spring:message code="label.description"/></td><td>${agentForm.description}</td></tr>
                        <tr><td><spring:message code="label.work.phone"/></td><td>${agentForm.workPhone}</td></tr>
                        <tr><td><spring:message code="label.cell.phone"/></td><td>${agentForm.cellPhone}</td></tr>
                        <tr><td><spring:message code="label.address.street"/></td><td>${agentForm.address}</td></tr>
                        <tr><td><spring:message code="label.city"/></td><td>${agentForm.city}</td></tr>
                        <tr><td><spring:message code="label.state"/></td><td>${agentForm.state}</td></tr>
                        <tr><td><spring:message code="label.zip.code"/></td><td>${agentForm.zipCode}</td></tr>                        
					</table>	
				  
				    
       				
		 		 </fieldset>
       		 
      </div>