<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%
String imagePath =request.getContextPath()+"/images/layoutImages/";	
%>

 
<script type="text/javascript">
	var gmailEmptyfields = {
	   	 'Email Address': '#emailAddress'
 	};
	
   function goForgetPassword() {
	
	   var emailAddress = document.forms[0].emailAddress.value;
	   var errorFlag=false;		   
		if (isEmpty(gmailEmptyfields)) {			
			var errorFlag=true;
		}
	    if (!errorFlag) { 		 	    	
	    	 document.getElementById("cProgressSamll").style.display="block";
	    	 document.forms["forgetPasswordForm"].action="forgetPassword.html";
			 document.forms["forgetPasswordForm"].submit();
		}
   }
 		
</script>

 <div id="cProgressSamll" style="display:none;">
	       <img style="margin-left:5px;margin-top:5px;" src="<%=imagePath%>animateCircle.gif" />	          
           <div id="emailSendStatus" ></div>	         		     
 </div> 
<div class="leftArea">
	<div id="errorBox" class="errorblock"></div>
 


	<form:form method="POST" name="forgetPasswordForm"  commandName="agentForm" enctype="multipart/form-data">
		<fieldset>
			 
			<legend>
				<span class="AccountCreateTableHeader">Forget Password Process	</span>
    		</legend>
			<br><span>Please Type the Email Address registered as an Agent </span> <br>
			<br>
			<table>
				<tr>
					<td style="text-align:left;width:100px;"><span
						id="emailAddressLabel"><font color="red">*</font>Email Address:</span></td>
					<td style="text-align:left;"><form:input path="emailAddress"
							size="57" maxlength="160" /> <label id="emailAddressMsg"
						class="errorMsg"></label> <br>
					<form:errors path="emailAddress" cssClass="errorMsg" /></td>
				</tr>
			</table>
			  <table style="border:0px;padding-left:105px;">
			    <tr><td>
			     <input type="button" value="Reset Password" class="buttonImage" onClick="javascript:goForgetPassword();">
			     </td></tr>
  			  </table>  			  
			 <br>
			 <br>
		</fieldset>
	</form:form>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/js/form/formUtils.js">
		
	</script>
	 



</div>


