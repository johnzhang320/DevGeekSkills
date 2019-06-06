<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!-- Front End Encryption library -->
<script type="text/javascript" src="js/lib/jquery-1.8.0.js"></script>
<script type="text/javascript" src="js/lib/jquery.jcryption-1.1.js"></script> 

<!-- Front End Encryption Customized Code -->
<script type="text/javascript"	src="js/utils/stringCryption.js">		
</script>
<script type="text/javascript"> 
	$(document).ready(function(){
		stringCryption.getPublicKey("/SpringbootAdvancedDemo/getKeyPair.html")
	 	stringCryption.initialize("password"); 		  
		stringCryption.initialize("creditNumber"); 		  
		stringCryption.initialize("socialSecurity"); 
	
	}); 
</script>  
<script type="text/javascript"> 
	function loginSubmitForm() { 	
		//document.forms['agentTable'].action='agentLogin.html';
		document.forms['agentTable'].submit();
	}
</script>

<div class="module-title" >
	<spring:message code="title.agent.login"/>  
	<input type="button" value="Help" class="helpbutton" onclick="javascript:popUpFixedName('/SpringbootAdvancedDemo/helpAgentLogin.html','920','920');">            
</div>
<div id="errorBox" class="errorblock"></div>
   

 

<form:form method="POST" name="agentTable" action="agentLogin.html" modelAttribute="agentTable" >
<fieldset>
	<legend>
	<span class="AccountCreateTableHeader"><spring:message code="title.agent.login"/></span>			 
	</legend>
	
	<br>
	<label style="margin-left: -100px;"  id="emailAddressAddressLabel">	<font color="red">*</font>     				 
	<spring:message code="label.username"/></label>
	<form:input path="emailAddress" size="180"  maxlength="250" cssClass="large" />   
	<form:errors path="emailAddress" cssClass="errorMsg" /> 
	<br>
	
	<label style="margin-left: -100px;"  id="passwordLabel">		      		 
	<font color="red">*</font><spring:message code="label.password"/></label>
	<form:password path="password"  size="180"  maxlength="450" cssClass="large" /> 
	<form:errors path="password" cssClass="errorMsg" /> 
	<br><br>
	
</fieldset>	
<fieldset>
	<legend>
	<span class="AccountCreateTableHeader"><spring:message code="title.creditcard.encrypt"/></span>			 
	</legend>	
	<label style="margin-left: -100px;"  id="label.credit.number">	<font color="red">*</font>     				 
	<spring:message code="label.credit.number"/></label>
	<form:input path="creditNumber" size="180"  maxlength="450" cssClass="large" />   
	<form:errors path="creditNumber" cssClass="errorMsg" /> 
	<br>
	<label style="margin-left: -100px;" id="cardHolderName">					 
		<spring:message code="label.card.holder.name"/></label>
		<form:input path="cardHolderName" size="180"  maxlength="450" />                       	 
		<form:errors path="cardHolderName" cssClass="errorMsg"  />       
		<br>
		<label style="margin-left: -100px;" id="expiringDate">				 
		<spring:message code="label.expiring.date"/></label>
		<form:input path="expiringDate" size="180"  maxlength="450"  />                       	 
		<form:errors path="expiringDate" cssClass="errorMsg"  />       
		<br>
		<label style="margin-left: -100px;" id="securityCode"> 		 
		<spring:message code="label.security.code"/></label>
		<form:input path="securityCode" size="180"  maxlength="450" />                       	 
		<form:errors path="securityCode" cssClass="errorMsg"  />       
		<br>				
</fieldset>
<fieldset>
	<legend>
	<span class="AccountCreateTableHeader"><spring:message code="title.socialsecurity.encrypt"/></span>			 
	</legend>	
	<label style="margin-left: -100px;"  id="social.security.number">		      		 
	<font color="red">*</font><spring:message code="social.security.number"/></label>
	<form:input path="socialSecurity"  size="180"  maxlength="450" cssClass="large" /> 
	<form:errors path="socialSecurity" cssClass="errorMsg" /> <br/>
	<label style="margin-left: -100px;" id="fullName">				 
		<spring:message code="label.full.name"/></label>
		<form:input path="fullName" size="180"  maxlength="450" />                       	 
		<form:errors path="fullName" cssClass="errorMsg"  />       
		<br>
	<label style="margin-left: -120px;" id="passwordLabel">&nbsp;	</label> 
	<input type="button" value="Submit" class="buttonLeft" onClick="javascript:loginSubmitForm();">
<!-- You can choose either display encrypted string or not display-->			             
	<br> <textArea id="showEncryptedString" rows="5" style="width:600px;display:none;" >			                 
	</textArea>         
	<br>
	<br>	
</fieldset>
</form:form>


<fieldset>
	<legend>
	 <span style="color:whilte">Front End Public Key Encryption Flow</span> 
	</legend>
  
     
   <img src="images/FrontEndPublicKeyEncryptionConcept.jpg" style="max-width:100%;max-height:100%">   
</fieldset>
  <img src="images/FEPKEDescription.jpg" style="max-width:100%;max-height:100%">

 


 


