<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
 <script type="text/javascript" src="<%=request.getContextPath()%>/js/utils/dialog.js"> </script>
   
 
 
  
   <div class="module-title" >
    <spring:message code="title.agent.signup"/>  
    <input type="button" value="Help" class="helpbutton" onclick="javascript:popUpFixedName('/helpAgentSingup.html','750','600');">  
           
   </div>
    
   <form:form id="agentTableId" action="agentSignup.html" method="POST" name="agentTable" commandName="agentTable">
                        
	     <div id="errorBox" class="errorblock">	 
	             
	     </div>
   		   <fieldset >  
   		     <legend><span ><spring:message code="title.agent.account"/></span> </legend>
       	     	<label  id="emailAddressAddressLabel">	<font color="red">*</font>     				 
      				     <spring:message code="label.username"/></label>
                      	 <form:input path="emailAddress" size="80"  maxlength="250" />                       	 
		      		     <form:errors path="emailAddress" cssClass="errorMsg"  />       
	      		 <br>
		      		 
       				 <label  id="passwordLabel">		      		 
		      		 		 <font color="red">*</font><spring:message code="label.password"/></label>
                  			<form:password path="password" size="80"  maxlength="250" /> 
		             	    <form:errors path="password" cssClass="errorMsg" /> 
            </fieldset>
       		   <fieldset >  
       			     <legend><span ><spring:message code="title.agent.information"/></span> </legend>
              		
     	 		    <label id="firstNameLabel"><font color="red">*</font><spring:message code="label.firstname"/>:</label>                   
                         <form:input path="firstName" size="40"  cssClass="firstName"/> 
		                 <form:errors path="firstName"  cssClass="errorMsg"/> 
		      		 <br>    
		      		   
	                <label id="lastNameLabel"><spring:message code="label.lastname"/>:</label>		                              
                 	     <form:input path="lastName" size="40" maxlength="50"/>
 		      		 <br>
         		 	
 		      		 <label id="licenseNoLabel"><font color="red">*</font> <spring:message code="label.license.no"/>:</label>		                              
                 	     <form:textarea path="dreNo" cssClass="smallNoteClass"/>
                 	     <form:errors path="dreNo" cssClass="errorMsg"/>
 		      		 <br>
         		   
         		    
         		   <label id="companyNameLabel"><font color="red">*</font><spring:message code="label.company.name"/>:</label>		                              
                 	     <form:input path="companyName" size="70" maxlength="250"/>
                 	       <form:errors path="companyName" cssClass="errorMsg"/>
 		      		 <br>
                    <label id="workPhoneLabel"><font color="red">*</font><spring:message code="label.work.phone"/>:</label>
                         <form:input path="workPhone" size="40"  maxlength="180" /> 
		                  <form:errors path="workPhone" cssClass="errorMsg" /> 
		      		 <br>	   
		      		     
                    <label id="cellPhoneLabel"><spring:message code="label.cell.phone"/>:</label>
                         <form:input path="cellPhone" size="40"  maxlength="180" /> 
		             
		      		 <br>
		      		     <label id="addressLabel"><spring:message code="label.address.street"/>:</label>
                         <form:input path="address" size="70"  maxlength="180" /> 
		                
		      		 <br>  
		      		 
		      	 	    <label id="addressLabel"><spring:message code="label.city"/>:</label>
                         <form:input path="city" size="40"  maxlength="180" /> 
		                 
		      		 <br>  
		      		
		      		<label id="stateLabel"><font color="red">*</font><spring:message code="label.state"/>:</label> 
                      <form:select path="state" cssClass="small">
                 	   <form:option value="" />
              	       <form:options items="${stateMap}" />
                     </form:select> 
                         <form:errors path="state" cssClass="errorMsg" /> 
		            <br>
		        	 		         
		             <label id="zipCodeLabel"><spring:message code="label.zip.code"/>:</label>                    
                         <form:input path="zipCode" size="40" />                 		 
                   
                     <br>
               </fieldset>     
                   <!-- pay attention to that 'value' should not be submit if <input tyoe="submit" value="submit"/> Spring form is not able to be submitted!!! -->
 				    <input type="submit" value=<spring:message code="submit.agent.form"/> class="buttonImage" >
			 </form:form>		
		 