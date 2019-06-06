<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ page import="com.loan.agent.services.Constant" %>	
 
 
	      
    
          <div id="errorBox" class="errorblock"></div>               
         
          	 
           	   <form:form   method="POST" name="emailServerForm" commandName="emailServerForm" enctype="multipart/form-data">              
            
                 <fieldset > 
                   <legend><span class="AccountCreateTableHeader">Please enter your Google Gmail Address and Password to set your email server </span> </legend>
                   <logic:present name="esConnectionStatus">
                      <br> <logic:equal name="esConnectionStatus" value="OK">                         
                           <span style="font-weight:bold; color:rgb(185,0,61); font-size:15px;">Congratulations, setup your email server successfully !</span>
                       </logic:equal>
                        <logic:notEqual name="esConnectionStatus" value="OK">
                           
                          <span style="font-weight:bold; color:red; font-size:15px;">Wrong username or password, please click <a href="javascript:popUpBase('https://accounts.google.com/ServiceLogin?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F%3Ftab%3Dwm&service=mail&ltmpl=default&dsh=-3400373251009505393', '600', '400')">here</a> to login Gmail to verify !</span>
                       </logic:notEqual>
                       <br>
                   </logic:present>
    
      		      <br> 
                  <span style="font-weight:bold;">View Setting Email Server User Guide, click <a href="javascript:popUpFullScreen('/emailUserGuides.html','750','600');">here</a> <br>  
                  <br>
      		      <span>Enter Your Gmail email address and Password</span>  <br><br>
                	  <table>
        				<tr>
        				<td style="text-align:left;width:100px;"> 
        				    <span  id="emailAddressLabel"><font color="red">*</font>Email Address:</span>
        				</td><td style="text-align:left;">
                      	 <form:input path="emailAddress" size="57"  maxlength="160" /> 
                      	    <img style="border:0px;padding-left:0px;padding-top:4px;" 
    			  			src="<%=request.getContextPath()%>/images/formImages/b_questionMark.png" 
    			  			title="Test if your Gmail account is able to connect Gmail Server, ensure using this Gmail account to able to send email to your customer ">
		      		      <label id="emailAddressMsg" class="errorMsg"></label>
		      			    
		      			    <br><form:errors path="emailAddress" cssClass="errorMsg" /> 
		      		     </td>
		      		     </tr>
		      		     <tr>
		      		     <td>
		      		     
		      			     <span  id="passwordLabel"><font color="red">*</font>Password:</span>
		      			 </td><td>
                  			<form:password path="password" size="57"  maxlength="160" /> 
		            	 <label id="passwordMsg" class="errorMsg"></label> 
		      			 <br><form:errors path="password" cssClass="errorMsg" /> 
		      			 </td>
		      			 </tr>
		      			</table> 
		      			    
              		 <table style="border:0px; padding-left:160px;">
  						<tr><td>
              		 	 <input type="button" value="Connecting" class="buttonImage" onClick="javascript:settingEmailServer();">
  						</td><td>
               		 	 <input type="button" value="Close" class="buttonImage" onClick="javascript:reloadParentCloseSelf();">
  						</td></tr>
  					</table>			
		      			<!-- 	<a  href="javascript:settingEmailServer();"><img style="border:0px;padding-left:160px;padding-top:20px;padding-bottom:20px;" 
    			    		src="<%=request.getContextPath()%>/images/formImages/b_setting.png" 
    			    		title="Setting Your Email Server" width="80" height="30" />
    			    		
    			    	  </a>   
    			    	 	<a  href="javascript:reloadParentCloseSelf();"><img style="border:0px;padding-left:20px;padding-top:20px;padding-bottom:20px;" 
    			    		src="<%=request.getContextPath()%>/images/formImages/b_close.png" 
    			    		title="Close Current Window" width="80" height="30" />
    			    		
    			    	   </a>   
    			    	   -->
    			    	   <br>
    			    	 <br>
    			 </fieldset>  	
    			  </form:form> 
           	 	 <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/formUtils.js"> </script>  
			  	 <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/configureEmailServer.js"> </script>  
		 	 
   					
            
         </div>
    
   
   