<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ page import="com.loan.agent.services.Constant" %>	
 
 
	<%
	    
	     String imagePath =request.getContextPath()+"/images/layoutImages/";
	%>          
    
          <div id="errorBox" class="errorblock"></div> 
          <div id="messageBox" class="messageblock"></div> 
          
           <div id="cProgressSamll" style="display:none;" style="algin:center;" class="progress_dialog">      
			 <img style="margin-left:30%;margin-top:20%;" src="<%=imagePath%>animateCircle.gif" />
			</div>        
            
       	   <form:form   method="POST" name="emailServerForm" commandName="emailServerForm" enctype="multipart/form-data" target="_self">
       	                    
               <form:hidden id="actionType" path="actionType" />
                 <fieldset > 
                   <legend><span class="AccountCreateTableHeader">Before sending bulk email to customers, send it to yourself to verify </span> </legend>
                   
    
      		        	  <table>
      		        	  
        				    <logic:present name="emailListErrorMessage" >
        				      <tr><td>
                          		  <span style="font-weight:bold; color:red; font-size:15px;"><bean:write name="emailListErrorMessage"/></span>
                          	   </td></tr>
                     		 </logic:present>
		      			 <tr><td>
		      			 	<label id="pictureContentLabel"><font color="red">*</font>Send to my address:</label>
                            <form:input path="fromEmailAddress" size="57"  maxlength="160" />
                           
		      			    <a  href="javascript:sendEmailToMyself()"><img style="border:0px;padding-top:3px;" 
    			    		src="<%=request.getContextPath()%>/images/formImages/b_send_to_me.png" 
    			    		title="Setting Your Email Server" width="90" height="30" /></a>
		      			  
		      			  <tr><td>
		      			      <label id="pictureContentLabel"><font color="red">*</font>Send by below email list:</label>	      			          			       		      			     	 
		      			    	 
		      			  </td></tr>
		      			  <tr><td>
		      			 	<form:textarea path="emailList" id="emailList"  cssClass="EmailList"/>	
		      			 </td></tr>
		      		 
		      			 
		      			</table> 	
		      				<a  href="javascript:sendByEmailList()"><img style="border:0px;padding-left:130px;padding-top:20px;padding-bottom:20px;" 
    			    		src="<%=request.getContextPath()%>/images/formImages/b_send_by_email_list.png" 
    			    		title="Setting Your Email Server" width="90" height="30" />
    			    		
    			    	  </a>   
    			    	 	<a  href="javascript:self.close();"><img style="border:0px;padding-left:20px;padding-top:20px;padding-bottom:20px;" 
    			    		src="<%=request.getContextPath()%>/images/formImages/b_close.png" 
    			    		title="Close Current Window" width="90" height="30" />
    			    		
    			    	   </a>   
    			    	   <br>
    			    	 
    			 </fieldset>  	
    			  </form:form> 
           	 	 <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/formUtils.js"> </script>  
			  	 <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/sendEmailDialog.js"> </script>  
		 	 
   					
            
        
   
   