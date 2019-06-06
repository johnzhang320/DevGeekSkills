<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%
	String imagePath = request.getContextPath()
			+ "/images/layoutImages/";
 String jQueryPath =request.getContextPath()+"/BuildJQuery/development-bundle/";
	String leftCapture = imagePath + "Agent_Login.png";
%>
<script type="text/javascript" src="<%=jQueryPath%>jquery-1.8.0.js"></script>
<script type="text/javascript" src="<%=jQueryPath%>jquery.jcryption-1.1.js"></script>

 
<div class="leftArea">
     <div class="module-title">
          Agent Login Loans-agent.com
       </div>
	<div id="errorBox" class="errorblock"></div>
    

    <logic:present name="passwordResetStatus" >
        <span style="color:red;font-size:13px;"><bean:write name="passwordResetStatus"/></span>
    </logic:present>

	<form:form method="POST" name="agentForm" commandName="agentForm" enctype="multipart/form-data">


		<fieldset>
			<logic:present name="agentLoginFor">
				<logic:equal name="agentLoginFor" value="agentLoginDirect">
					<legend>
						<span class="AccountCreateTableHeader">Agent Login Directly
						</span>
					</legend>
				</logic:equal>

				<logic:equal name="agentLoginFor" value="AgentReviewQuote">
					<legend>
						<span class="AccountCreateTableHeader">Agent Login for
							Reviewing Quotes </span>
					</legend>
				</logic:equal>
				<logic:equal name="agentLoginFor" value="NicheProgram">
					<legend>
						<span class="AccountCreateTableHeader">Agent Login for
							Entering Niche Program </span>
					</legend>
				</logic:equal>
				<logic:equal name="agentLoginFor" value="RateSheetForm">
					<legend>
						<span class="AccountCreateTableHeader">Agent Login for
							Entering Interest Rates </span>
					</legend>
				</logic:equal>
				<logic:equal name="agentLoginFor" value="AgentReplyQuoteLogin">
					<legend>
						<span class="AccountCreateTableHeader">Agent Login for
							Reply Borrower Quote </span>
					</legend>
				</logic:equal>
				<logic:equal name="agentLoginFor" value="EmailMarketing">
					<legend>
						<span class="AccountCreateTableHeader">Agent Login for
							Email Marketing </span>
					</legend>
				</logic:equal>
			</logic:present>

			<span>Email and Password are your Google Gmail Email and password </span> <br>
			<br>
			<table>
				<tr>
					<td style="text-align:left;width:100px;"><span
						id="emailAddressLabel"><font color="red">*</font>Email
							Address:</span></td>
					<td style="text-align:left;"><form:input path="emailAddress"
							size="57" maxlength="160" /> <label id="emailAddressMsg"
						class="errorMsg"></label> <br>
					<form:errors path="emailAddress" cssClass="errorMsg" /></td>
				</tr>
				<tr>
					<td><span id="passwordLabel"><font color="red">*</font>Password:</span>
					</td>
					<td><form:password path="password" size="57" maxlength="160" />
						<label id="passwordMsg" class="errorMsg"></label> <br>
					<form:errors path="password" cssClass="errorMsg" /></td>
				</tr>
			</table>
			  <table style="border:0px;padding-left:105px;">
			    <tr><td>
			     <input type="button" value="Login" class="buttonImage" onClick="javascript:loginSubmitForm();">
			     </td></tr>
  			  </table>  			  
			
			 <a href="<%=request.getContextPath()%>/agentSignup.html"><span>Sign Up</span></a><br><br>
			 <a href="javascript:forgetPassword();"><span>Forget Password</span></a><br><br>	
			 
			 
			 <br>
			 <br>
		</fieldset>
	</form:form>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/form/formUtils.js">
		
	</script>
	<!-- Front End Encryption -->
	 
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/utils/stringCryption.js">		
	</script>
   
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/form/agentLogin.js">
		
	</script>
	
	


</div>


