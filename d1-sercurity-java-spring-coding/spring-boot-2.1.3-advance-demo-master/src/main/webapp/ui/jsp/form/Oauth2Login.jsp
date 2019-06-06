<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
 
<%
 
 String jQueryPath =request.getContextPath()+"/ui/static/js/lib/";
 String jFromPath =request.getContextPath()+"/ui/static/js/form/";
 
%> 
 <script type="text/javascript" src="js/lib/jquery-1.8.0.js"></script>
 <script type="text/javascript" src="js/lib/jquery.jcryption-1.1.js"></script>
 <script type="text/javascript"	src="js/utils/stringCryption.js"></script>
 <script type="text/javascript"	src="js/form/agentLogin.js"></script>
 <script type="text/javascript">
 	$(document).ready(function(){
 		stringCryption.getPublicKey("/SpringbootAdvancedDemo/getKeyPair.html")
 	}); 
 </script>
<!--

//-->

<div class="module-title" >
	<spring:message code="title.secure.password.sso"/>  
	<input type="button" value="Help" class="helpbutton" onclick="javascript:popUpFixedName('/SpringbootAdvancedDemo/helpAgentLogin.html','920','600');">            
</div>
<div id="errorBox" class="errorblock"></div>
   

 

<form:form method="POST" name="agentTable" action="oauth2Login.html" modelAttribute="agentTable" >
<fieldset>
	<legend>
	<span class="AccountCreateTableHeader"><spring:message code="title.secure.password.sso"/></span>			 
	</legend>
	
	<br>
	<label style="margin-left: -100px;"  id="username">	<font color="red">*</font>     				 
	<spring:message code="label.username"/></label>
	<form:input path="emailAddress" size="180"  maxlength="250" cssClass="large" />   
	<form:errors path="emailAddress" cssClass="errorMsg" /> 
	<br>
	
	<label style="margin-left: -100px;"  id="passwordLabel">		      		 
	<font color="red">*</font><spring:message code="label.password"/></label>
	<form:password path="password"  size="180"  maxlength="450" cssClass="large" /> 
	<form:errors path="password" cssClass="errorMsg" /> 
	<br><br>
	<label style="margin-left: -120px;" id="passwordLabel">&nbsp;	</label> 
	<input type="button" value="Login" class="buttonLeft" onClick="javascript:loginSubmitForm();">
	<!-- You can choose either display encrypted string or not display-->			             
	<br> <textArea id="showEncryptedString" rows="5" style="width:600px;display:none;" >			                 
	</textArea>         
	<br>
	<br>
	<img src="images/PasswordSecureSSO.jpg" style="max-width:100%;max-height:100%"/>
</fieldset>
</form:form>

<!-- Front End Encryption -->
 
<script type="text/javascript"	src="js/utils/stringCryption.js">		
</script>
  
<script type="text/javascript"	src="js/form/agentLogin.js">		
</script>


 


