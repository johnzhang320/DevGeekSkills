<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ page import="com.loan.agent.services.Constant" %> 

<%
     
       
	   String imagePath =request.getContextPath()+"/images/layoutImages/";
	   
	   Integer agentId =(Integer) request.getSession().getAttribute("loginAgentId");    
	    String loginAgentId =null;
	   if (null!=agentId) {
	  	  loginAgentId = agentId.toString() ;
	   }
	   request.getSession().setAttribute("loginAgentIdStr",loginAgentId);    
	    
	   String  certPicCode =(String)  request.getSession().getAttribute(Constant.CERT_PIC_CODE_STRING);	
	    String jQueryPath =request.getContextPath()+"/BuildJQuery/development-bundle/";
 %>
<style type="text/css">
  .smallMultiple1 {
  	border: 1px solid;
  	width:160px;
  	height:120px;
  }
</style>
 <script type="text/javascript" src="<%=jQueryPath%>jquery.jcryption-1.1.js"></script>
 <script type="text/javascript" src="<%=request.getContextPath()%>/js/utils/stringCryption.js"></script> 
 
 <script type="text/javascript">
 
$(function(){
    Test = {
        UpdatePreview: function(obj){        
          
          // if IE < 10 doesn't support FileReader
          if(!window.FileReader){
             // don't know how to proceed to assign src to image tag
             document.getElementById("loadDiv").style.display="block";
             var form=document.forms[0];
             form.setAttribute("action", "agentSignup.html?uploadPicture=yes");
             form.submit(); 
          } else {
             var reader = new FileReader();
             var target = null;

             reader.onload = function(e) {
              target =  e.target || e.srcElement;
               document.getElementById("loadDiv").style.display="block";
               document.getElementById("loadLoginAgentDiv").style.display="none";
               $("#agentImage").attr("src", target.result);
             };
              reader.readAsDataURL(obj.files[0]); 
          }
        }
    };
});

function fileUpload(form, action_url, div_id) {
    // Create the iframe...
    var iframe = document.createElement("iframe");
    iframe.setAttribute("id", "upload_iframe");
    iframe.setAttribute("name", "upload_iframe");
    iframe.setAttribute("width", "0");
    iframe.setAttribute("height", "0");
    iframe.setAttribute("border", "0");
    iframe.setAttribute("style", "width: 0; height: 0; border: none;");
 
    // Add to document...
    form.parentNode.appendChild(iframe);
    window.frames['upload_iframe'].name = "upload_iframe";
 
    iframeId = document.getElementById("upload_iframe");
 
    // Add event...
    var eventHandler = function () {
 
            if (iframeId.detachEvent) iframeId.detachEvent("onload", eventHandler);
            else iframeId.removeEventListener("load", eventHandler, false);
 
            // Message from server...
            if (iframeId.contentDocument) {
                content = iframeId.contentDocument.body.innerHTML;
            } else if (iframeId.contentWindow) {
                content = iframeId.contentWindow.document.body.innerHTML;
            } else if (iframeId.document) {
                content = iframeId.document.body.innerHTML;
            }
 
            document.getElementById(div_id).innerHTML = content;
 
            // Del the iframe...
            setTimeout('iframeId.parentNode.removeChild(iframeId)', 250);
        }
 
    if (iframeId.addEventListener) iframeId.addEventListener("load", eventHandler, true);
    if (iframeId.attachEvent) iframeId.attachEvent("onload", eventHandler);
 
    // Set properties of form...
    form.setAttribute("target", "upload_iframe");
    form.setAttribute("action", action_url);
    form.setAttribute("method", "post");
    form.setAttribute("enctype", "multipart/form-data");
    form.setAttribute("encoding", "multipart/form-data");
 
    // Submit the form...
    form.submit();
 
    document.getElementById(div_id).innerHTML = "Uploading...";
    
}


</script>
  <br> 			    
   <div class="module-title" >
         Agent Sign Up 
         <input type="button" value="Help" class="helpbutton" onclick="javascript:popUpFullScreen('/websiteHelp.html?helpTab=tabs-2','750','600');">
         
   </div>
    <div id="cProgressSamll" style="display:none;">
	       <img style="margin-left:170px;margin-top:140px;" src="<%=imagePath%>animateCircle.gif" />
	</div>
   <form:form id="agentFormId" action="agentSignupProcess.html" method="POST" name="agentForm" commandName="agentForm" enctype="multipart/form-data">
              <input type="hidden" name="loginAgentId" id="loginAgentId" value="<%=loginAgentId%>"/>
              <input type="hidden" name="realCertPicCode" id="realCertPicCode" value="<%=certPicCode%>"/>
              
	    	   <div id="errorBox" class="errorblock"></div>
	    	 	 
	    	  
       		   <fieldset >  
       			<logic:notPresent name="loginAgentId">  
       	          <legend><span >Using your Google Gmail email address and the password to fill below email address and password</span> </legend> 
       			</logic:notPresent>
       	     	<logic:present name="loginAgentId">  
       	          <legend><span >Check your Gmail Address. If you change Gmail Address, then use "Forget Password" to change password</span> </legend> 
       			</logic:present>
       	     	
       	     	<br> <label  id="emailAddressLabel">      				 
      				     <font color="red">*</font>     				 
      				 Gmail Email Address:</label>
                      	 <form:input path="emailAddress" size="80"  maxlength="250" />                       	 
		      		 <label id="emailAddressMsg" class="errorMsg"></label>
		      		    <input type="button" value="Why Gmail" class="helpbutton" onclick="javascript:popUpFullScreen('/websiteHelp.html?helpTab=tabs-51','750','600');">
		      		 
		      		 <br>
		      		 
		      	
		      	       <logic:notPresent name="loginAgentId">
		      				 <label  id="passwordLabel">		      		 
		      		 		 <font color="red">*</font>Gmail Password:</label>
                  			<form:password path="password" size="80"  maxlength="250" /> 
		             	<label id="passwordMsg" class="errorMsg"></label> 
		      			 <br>	 
	    	     	</logic:notPresent>
  	           <label  id="emailAddressLabel" style="font-weight:bold;">Test Gmail Connection: </label>  	          
  	           <input type="button" value="Connecting" class="buttonImage" onClick="javascript:doEmailTest();">
    	       <br><br>
  		
  	         <span style="font-weight:bold;">If you do not have Gmail Account , Click <a href="javascript:popUpFixedName('https://accounts.google.com/SignUp?service=mail&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F%3Ftab%3Dwm&ltmpl=default','650','500');">here</a>  to create one, If you need to verify your Gmail Account ,Click  
  	         <a href="javascript:popUpFixedName('https://accounts.google.com/ServiceLogin?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F%3Ftab%3Dwm&service=mail&ltmpl=default','650','500');">here</a>. More Detail Configure Help, Click  <a  href="javascript:popUpFullScreen('/websiteHelp.html?helpTab=tabs-5','750','600');"><span style="margin-top:20px;">here</span></a>  
  	          </span><br>
			 
          </fieldset>
       		   <fieldset >  
       			     <legend><span class="AccountCreateTableHeader">Agent Information</span> </legend>
       			     
       			     
      					<label id="pictureContentLabel">Upload Your Picture File(Less W288xH388 pixels):</label>
                         <input id="fileContent" type="file" name="pictureContent" size="70" maxlength="280" onChange="Test.UpdatePreview(this)" /> 
 		          	 	 <label id="pictureContentMsg" class="errorMsg"></label>    		 
		                           
		              <br>
		              
                	     <div id="loadDiv" style="display:none;">
                	      	 <label id="pictureContentLabel">Picture Photo:</label>
                	   	    <img width="133"  alt="" id="agentImage"  />    
                	   	    <br>           	   	 
		                </div>
		                 
		                <div id="loadLoginAgentDiv" >
                	      	<label id="pictureContentLabel">Picture Photo:</label>
                	   	    <img width="133"  alt="" id="agentImage" src="<%=request.getContextPath()%>/getLoginAgentPicture.html" />    
  		               </div> 
		              <br>
		          	 <logic:present name="uploadWebFile"> 	
		              <label id="pictureContentLabel"><font color="red">*</font>Picture Photo:</label>
 		             	<img  width="133" height="160" alt="" src='<bean:write name="agentEmailPictureURL"/>'>
 		             	<br>
		  			 </logic:present> 
		  			 
      			    
		      		
     	 		    <label id="firstNameLabel"><font color="red">*</font>First Name:</label>                   
                         <form:input path="firstName" size="40"  cssClass="firstName"/> 
		            <label id="firstNameMsg" class="errorMsg"></label> 
		      		 <br>    
		      		   
	                <label id="lastNameLabel">Last Name:</label>		                              
                 	     <form:input path="lastName" size="40" maxlength="50"/>
 		      		 <br>
 		      		 
 		      	    <label id="expertise" >Expertise and Selling Points(500 Characters):</label>
           			 
           				<form:textarea path="description"   cssClass="noteClass"/> 		 
		      		<br> 
 		      		
         		  
         		 	<label id="pictureContentLabel">Upload Loan Application Form:</label>
                         <input id="appFormFile" type="file" name="appFormFile" size="70" maxlength="280"  /> 
 		          	 	 <label id="appFormFiletMsg" class="errorMsg"></label>    		 
		            <br>
 		      		 <label id="licenseNoLabel"><font color="red">*</font>State Real Estate Sales License No(maybe Multiple):</label>		                              
                 	     <form:textarea path="dreNo" cssClass="smallNoteClass"/>
                 	       <label id="licenseNoMsg" class="errorMsg"></label> 
 		      		 <br>
         		  	 <label id="licenseNoLabel">NMLS Number:</label>		                              
                 	     <form:input path="nmlsNo" size="40" maxlength="250"/>
                 	       <label id="licenseNoMsg" class="errorMsg"></label> 
 		      		 <br>
         		     <label id="licenseEligibleStateLabel"><font color="red">*</font>License Eligible States:</label>		                              
                 	     <form:select path="licenseEligibleState" cssClass="smallMultiple1" multiple="true">                 	   		
              	      		 <logic:present name="stateSelectedList">
              	      		 	<logic:iterate id="thisState" name="stateSelectedList" type="com.loan.agent.calculators.vo.SelectedStateVo" >
              	      		 	    <logic:equal name="thisState" property="selected" value="false">
              	      		 	    	<option value='<bean:write name="thisState" property="stateKey"/>'><bean:write name="thisState" property="stateName"/></option>
              	      		 	    </logic:equal>
              	      		 		 <logic:equal name="thisState" property="selected" value="true">
              	      		 	    	<option value='<bean:write name="thisState" property="stateKey"/>' selected ><bean:write name="thisState" property="stateName"/></option>
              	      		 	    </logic:equal>
              	      		 	</logic:iterate>
              	      		 </logic:present>  
                     	</form:select>  
                      <label id="licenseEligibleStateMsg" class="errorMsg"></label> 	
		        	<br>
         		   <label id="companyNameLabel"><font color="red">*</font>Company Name:</label>		                              
                 	     <form:input path="companyName" size="70" maxlength="250"/>
                 	       <label id="companyNameMsg" class="errorMsg"></label> 
 		      		 <br>
                    <label id="workPhoneLabel"><font color="red">*</font>Work Phone No:</label>
                         <form:input path="workPhone" size="40"  maxlength="180" /> 
		            <label id="workPhoneMsg" class="errorMsg"></label> 
		      		 <br>	   
		      		     
                    <label id="cellPhoneLabel">Cell Phone No:</label>
                         <form:input path="cellPhone" size="40"  maxlength="180" /> 
		            <label id="cellPhoneMsg" class="errorMsg"></label> 
		      		 <br>
		      		<label id="addressLabel">Address:</label>
                         <form:input path="address" size="70"  maxlength="180" /> 
		            <label id="addressMsg" class="errorMsg"></label> 
		      		 <br>  
		      		 
		      	 	<label id="addressLabel">City:</label>
                         <form:input path="city" size="40"  maxlength="180" /> 
		            <label id="cityMsg" class="errorMsg"></label> 
		      		 <br>  
		      		
		      		<label id="stateLabel"><font color="red">*</font>Living State:</label> 
                      <form:select path="state" cssClass="small">
                 	   <form:option value="California" />
              	       <form:options items="${stateMap}" />
                     </form:select> 
                      <label id="stateMsg" class="errorMsg"></label> 	
		         
		        	<br>
		        		         
		             <label id="zipCodeLabel">Zip Code:</label>                    
                         <form:input path="zipCode" size="40" />                 		 
                  	 <label id="zipCodeMsg" class="errorMsg"></label> 
                     <br>
               </fieldset>     
                     
               <fieldset >  
       			     <legend><span class="AccountCreateTableHeader">Enter Text Security Code, Verify Term and Agreement and then Submit the Form</span> </legend>
       			     <table cellspacing="0" cellpadding="0">
       			     <tr>
       			      <td>
       			     	 <span style="margin-bottom:0px;" >Text Code(6 digits):</span>    
       		         </td>
       			     <td>
       			     	   <img style="margin-left:0px;" id="certImageCode" src="<%=request.getContextPath()%>/generateCertPicCode.html?certPicCodeDigits=6"><br>      
       		          </td>
       		            <td>      		                
       		                 &nbsp;&nbsp;<a style="margin-left:-80px;" href="javascript:changeCertPicCode('6');">Change the code</a>
       		           </td>
       		         
       		           </tr>
       		           <tr>
       		           <td>
         		            <span style="margin-bottom:70px;" id="verifycodeLabel">
       		                <span id="enteredCertPicCodeLabel">Enter Text Code:</span>    
       		            </span>  
       		          </td>
       		           <td> 
       		              <input type="text" id="enteredCertPicCode" name="enteredCertPicCode" size="30" />                 		 
                  	   
                  		&nbsp; 
                  	      <span id="enteredCertPicCodeMsg" class="errorMsg"></span> 
                  	   </td>
                  	   </tr>
                  	     <tr>
                  	      <td>&nbsp;</td>
                  	      <td >
   	             		 	Please carefully read:   <a href="javascript:popUpFixedName('/termAndAgreement.html','780','700');"><span style="color:black; weight:bold; font-size:14px;">Term and Agreement</span></a>	         		       
   						 </td>
   						</tr>  
   						 
   						<tr>
   						 <td>&nbsp;</td>
   						  <td>
                  	          <table><tr><td><input style="margin-bottom:10px;" type="checkbox" name="termAgreement" id="termAgreement" value="Aceept"></td><td>I accept Term and Agreement</td></tr></table>
                  	          	&nbsp; 
                  	      	  <span id="termAgreementMsg" class="errorMsg"></span> 
                  	          
                  	      </td>
                  	       <td>
                  	          &nbsp;
                  	      </td>
   						</tr>  
       		          <tr>
       		            <td>&nbsp;</td>
       		          <td>    
       		            		          
					   <input type="button" value="Submit" class="buttonImage" onClick="signUpSubmitForm();">
             		 </td><td>
		     		    <td>&nbsp;</td>
					 </tr>
					</table>       
               </fieldset>
            
			 </form:form>		
					 <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/formUtils.js"> </script>  
					
					<script type="text/javascript" src="<%=request.getContextPath()%>/js/form/agentSignup.js"> </script>  
