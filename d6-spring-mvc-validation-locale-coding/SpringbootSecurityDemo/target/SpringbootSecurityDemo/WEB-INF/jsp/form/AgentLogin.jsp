<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%
 
 String jQueryPath =request.getContextPath()+"/js/lib/";
 String jFromPath =request.getContextPath()+"/js/form/";
 
%>
 
<script type="text/javascript" src="<%=jQueryPath%>jquery-1.8.0.js"></script>
<script type="text/javascript" src="<%=jQueryPath%>jquery.jcryption-1.1.js"></script>
 
<div class="module-title" >
	<spring:message code="title.agent.login"/>  
	<input type="button" value="Help" class="helpbutton" onclick="javascript:popUpFixedName('/Spring4AngularHibernateTile3RestCassandra/helpAgentLogin.html','920','600');">            
</div>
<div id="errorBox" class="errorblock"></div>
   

<logic:present name="passwordResetStatus" >
	<span style="color:red;font-size:13px;"><bean:write name="passwordResetStatus"/></span>
</logic:present>

<form:form method="POST" name="agentTable" action="agentLogin.html" commandName="agentTable" >
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
	<label style="margin-left: -120px;" id="passwordLabel">&nbsp;	</label> 
	<input type="button" value="Login" class="buttonLeft" onClick="javascript:loginSubmitForm();">
	<!-- You can choose either display encrypted string or not display-->			             
	<br> <textArea id="showEncryptedString" rows="5" style="width:600px;display:none;" >			                 
	</textArea>         
	<br>
	<br>
</fieldset>
</form:form>

<!-- Front End Encryption -->
 
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/utils/stringCryption.js">		
</script>
  
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/form/agentLogin.js">		
</script>


 


