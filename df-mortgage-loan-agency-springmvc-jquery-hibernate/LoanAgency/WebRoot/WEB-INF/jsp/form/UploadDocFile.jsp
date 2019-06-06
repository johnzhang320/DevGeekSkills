<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ page import="com.loan.agent.services.Constant" %>	
 
 
	<%
	     String connectionStatus = (String) request.getSession().getAttribute(Constant.EMAIL_SERVER_CONNECTION_STATUS);
	     String imagePath =request.getContextPath()+"/images/layoutImages/";
	%>          
    
          <div id="errorBox" class="errorblock"></div> 
           <div id="cProgressSamll" style="display:none;" style="algin:center;" class="progress_dialog">      
			 <img style="margin-left:30%;margin-top:20%;" src="<%=imagePath%>animateCircle.gif" />
			</div>        
         
  	 
  	   
  	      <div class="module-title">
  	          Upload Email List
  	        	 <input type="button" value="Help" class="helpbutton" onclick="javascript:popUpFullScreen('/websiteHelp.html?helpTab=tabs-7','750','600');">
      
  	      </div>    
  	    
       	   <form:form   method="POST" name="emailServerForm" commandName="emailServerForm" enctype="multipart/form-data" target="_self">
       	                    
               <form:hidden id="actionType" path="actionType" />
               <form:hidden id="firstNamePtr" path="firstNamePtr" />
               <form:hidden id="emailPtr" path="emailPtr" />
           
                 <fieldset > 
                   <legend><span class="AccountCreateTableHeader">View Procedures by pressing 'Help' button </span> 
                     
                   </legend>
                     	<label id="pictureContentLabel" style="margin-left:-30px;"><font color="red">*</font>Upload Email List File:</label>
                        
                            <input id="fileContent" type="file" name="fileContent" size="65" maxlength="280" style="width:300px;" onChange="javascript:uploadDocFile();" /> 
                          
                         <br>
                         <logic:present name="EmailDynamicList" >
  	         				  <input type="button" value="Confirm" class="buttonLeft"   onClick="javascript:confirmChoose();">
  	         			 </logic:present> 
  	         			   <logic:notPresent name="EmailDynamicList" >    	
		      		    	  <input type="button" value="Save Email List" class="buttonImage"   onClick="javascript:downloadEmailContent();">
  	         			      <input type="button" value="Apply Email List" class="buttonLeft"   onClick="javascript:applyEmailList();">
  	         			  </logic:notPresent>        
  	      				    <input type="button" value="Close" class="buttonLeft"   onClick="javascript:self.reloadParentCloseSelf();"> <br>           			     
  	      				  <logic:present name="currentUploadingFile" >
  	          				 <br>The Uploaded Filename:<span style="font-weight:bold; color:blue; font-size:13px;"> <bean:write name="currentUploadingFile"/></span>
              				 <br>
  	      				 </logic:present>  
      		        	  <table>
      		        	  
        				    <logic:present name="emailListErrorMessage" >
        				      <tr><td>
                          		  <span style="font-weight:bold; color:red; font-size:15px;"><bean:write name="emailListErrorMessage"/></span>
                          	   </td></tr>
                     		 </logic:present>
		      			 <tr><td>
		      			 
		      			      
                           
	      			     </td></tr>
		      			  	<logic:present name="EmailDynamicList" >
		      			 	 <tr>
		      			     	 <td>
		      			 	      	<jsp:include page="SelectEmailAndFirstName.jsp" flush="true" />
		      			      	</td>
		      				 </tr>
		      		     </logic:present>
		      		    <logic:notPresent name="EmailDynamicList" >  
		      			  <tr><td>
		      			 	<form:textarea path="emailList" id="emailList"  cssClass="EmailList"/><br/> 	
		      			 </td></tr>
		      		     </logic:notPresent>
		      			 
		      			</table>
		      			  <logic:notPresent name="EmailDynamicList" >    	
	 						 <input type="button" value="Save Email List" class="buttonImage"   onClick="javascript:downloadEmailContent();">
  	         			      <input type="button" value="Apply Email List" class="buttonLeft"   onClick="javascript:applyEmailList();">
  	         	  	       </logic:notPresent>
  	         			     
  	         			 <logic:present name="EmailDynamicList" >
  	         				  <input type="button" value="Confirm" class="buttonLeft"   onClick="javascript:confirmChoose();">
  	         			 </logic:present>         
  	              		  <input type="button" value="Close" class="buttonLeft"   onClick="javascript:self.reloadParentCloseSelf();">
  		      			   
    			 </fieldset>  	
    			  </form:form> 
            	  <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/formUtils.js"> </script>  
			      <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/uploadDocFile.js"> </script>   
      
   
   