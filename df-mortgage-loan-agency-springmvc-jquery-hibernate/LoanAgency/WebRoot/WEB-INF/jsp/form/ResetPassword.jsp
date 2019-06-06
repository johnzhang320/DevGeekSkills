<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%
  String jQueryPath =request.getContextPath()+"/BuildJQuery/development-bundle/";
 %>
<script type="text/javascript" src="<%=jQueryPath%>jquery.jcryption-1.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/utils/stringCryption.js"></script> 

 
 

<div class="leftArea">
	<div id="errorBox" class="errorblock"></div>
 


	<form:form method="POST" name="agentForm" commandName="agentForm" action="resetPassword.html" enctype="multipart/form-data">


		<fieldset>
			 
					<legend>
						<span class="AccountCreateTableHeader">Reseting Password  
						</span>
					</legend>
				 

				 

			<span>Please Type New Password and Confirm </span> <br>
			<br>
			<table>
				<tr>
					<td><span id="passwordLabel">New Password:</span>
					</td>
					<td><form:password path="password" id="password" size="57" maxlength="160" />
						<label id="passwordMsg" class="errorMsg"></label> <br>
					<form:errors path="password" cssClass="errorMsg" /></td>
				</tr>
				 <tr>
					<td><span id="passwordLabel">Confirm New Password:</span>
					</td>
					<td><form:password path="confirmPassword" id="confirmPassword" size="57" maxlength="160" />
						<label id="confirmPasswordMsg" class="errorMsg"></label> <br>
					<form:errors path="confirmPassword" cssClass="errorMsg" /></td>
				</tr>
			</table>
			  <table style="border:0px;padding-left:105px;">
			    <tr><td>
			     <input type="button" value="Reset Password" class="buttonImage" onClick="javascript:resetPassword();">
			     </td></tr>
  			  </table>  			  
			
		 
			 <br>
			 <br>
		</fieldset>
	</form:form>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/js/form/formUtils.js"></script>
	 
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/js/form/resetPassword.js"></script>
	 



</div>


