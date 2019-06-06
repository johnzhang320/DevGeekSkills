<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
  
<%
     
       
	   String imagePath =request.getContextPath()+"/images/layoutImages/";
	   
	   Integer agentId =(Integer) request.getSession().getAttribute("loginAgentId");    
	    String loginAgentId =null;
	   if (null!=agentId) {
	  	  loginAgentId = agentId.toString() ;
	   }
	   request.getSession().setAttribute("loginAgentIdStr",loginAgentId);    
 %>
<style type="text/css">
  .smallMultiple1 {
  	border: 1px solid;
  	width:160px;
  	height:120px;
  }
</style>
 
 
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
    <div id="cProgressSamll" style="display:none;">
	       <img style="margin-left:170px;margin-top:140px;" src="<%=imagePath%>animateCircle.gif" />
	</div>
   <form:form id="agentFormId" action="agentSignupProcess.html" method="POST" name="agentForm" commandName="agentForm" enctype="multipart/form-data">
              <input type="hidden" name="loginAgentId" id="loginAgentId" value="<%=loginAgentId%>"/>
        
	    	   <div id="errorBox" class="errorblock"></div>
	    	    <fieldset >  
       			     <legend><span class="AccountCreateTableHeader">Agent Sign Up</span> </legend>
       			     
       			       <fieldset >  
       	     <legend><span >Using your Google Gmail email address and the password to fill below email address and password</span> </legend> 
       	     
       	     	 <br><label  id="emailAddressLabel">      				 
      				     <font color="red">*</font>     				 
      				 Gmail Email Address:</label>
                      	 <form:input path="emailAddress" size="80"  maxlength="250" /> 
		      		 <label id="emailAddressMsg" class="errorMsg"></label>
		      		 <br>
		      		 
		      		 <label  id="passwordLabel">
		      		 
		      		 	<font color="red">*</font>
		      		 
		      		Gmail Password:</label>
                  		<form:password path="password" size="50"  maxlength="250" /> 
		             <label id="passwordMsg" class="errorMsg"></label> 
		      		 <br>	 
		      		 
       	     
       	     
       	     
  	           <label  id="emailAddressLabel" style="font-weight:bold;">Test Gmail Connection: </label>  	          
  	           <input type="button" value="Connecting" class="buttonImage" onClick="javascript:doEmailTest();">
  			
  	          <!-- a  href="javascript:doEmailTest()">
  	                <img style="border:0px;padding-left:0px;padding-top:0px;" 
    			  	src="<%=request.getContextPath()%>/images/formImages/b_set_email_server.png" 
    			  	title="Setting up your email server using a goole email account"
    			   width="85" height="30" />
 		      </a>  -->
  	           <br>
  		
  	         <span style="font-weight:bold;">If you do not have Gmail Account , click <a href="javascript:popUpFixedName('https://accounts.google.com/SignUp?service=mail&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F%3Ftab%3Dwm&ltmpl=default','650','500');">here</a>  to create one, If you need to verify your Gmail Account ,Click  
  	         <a href="javascript:popUpFixedName('https://accounts.google.com/ServiceLogin?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F%3Ftab%3Dwm&service=mail&ltmpl=default','650','500');">here</a> </span><br>

          </fieldset>
       			     
       			     
      					<label id="pictureContentLabel">Upload Your Picture File:</label>
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
 		      		 <label id="licenseNoLabel"><font color="red">*</font>DRE Number:</label>		                              
                 	     <form:input path="dreNo" size="40" maxlength="250"/>
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
          
              		  <div style="padding-left:80px;margin-bottom:20px">
               		    <input type="button" value="Submit" class="buttonImage" onClick="javascript:signUpSubmitForm();">
  			   
    			    	<!-- 	<a  href="javascript:signUpSubmitForm()"><img style="border:0px;" 
    			    			src="<%=request.getContextPath()%>/images/formImages/b_signup.png" 
    			    			title="Next Pag" width="80" height="30" />
    			    		</a>
    			    	  	      -->	   			     
					</div>   
			 </form:form>		
					 <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/formUtils.js"> </script>  
					
					<script type="text/javascript" src="<%=request.getContextPath()%>/js/form/agentLogin.js"> </script>  
