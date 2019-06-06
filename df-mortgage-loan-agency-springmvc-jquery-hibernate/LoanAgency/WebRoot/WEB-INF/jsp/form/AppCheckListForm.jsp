<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ page language="java" import ="com.loan.agent.mvc.formbean.AppCheckListForm" %> 
<%@ page import="com.loan.agent.services.Constant" %>	
 <style>
<!--
  .appCheckListTitle {
  	width:510px;
  	height:70px;
  	border : #000000 solid 1px; 
  	overflow:scroll-y;
  }
   .appCheckListNote {
  	width:510px;
  	height:140px;
  	border : #000000 solid 1px; 
  	overflow:scroll-y;
  }
-->
</style>
  <script  type="text/javascript">
 
      
   function refreshParent() {
       window.opener.location.reload(false);
       self.close();   	    
       
   }
       
      
  </script>
	<%
	     
	     String imagePath =request.getContextPath()+"/images/layoutImages/";
	    
	  	     
	     String actionType =(String) request.getSession().getAttribute(Constant.ACTION_TYPE);
	     String submitAction="Submit";
	     String ActionTitle="Add a ";
	    	if (Constant.ACTION_CHECK_LIST_EDIT.equalsIgnoreCase(actionType)) {
	    		ActionTitle="Edit the ";
	    	}
	    	if ( Constant.ACTION_CHECK_LIST_DELETE.equalsIgnoreCase(actionType)) {		
	    		ActionTitle="Delete the ";	
	    		submitAction="Delete";	
	    	}		 
	
	     
	%>          
    
          <div id="errorBox" class="errorblock"></div> 
             <div id="cProgressSamll" style="display:none;" style="algin:center;" class="progress_dialog">      
			     <img style="margin-left:30%;margin-top:20%;" src="<%=imagePath%>animateCircle.gif" />
			</div> 
			       
             <logic:present name="successMessage" >
        	   <div id="saveSuccess" class="saveSuccess">
              	  <span style="font-weight:bold; color:GREEN; font-size:15px;"><bean:write name="successMessage"/></span>
               </div> 
             </logic:present>
       	   <form:form  id="appCheckList_form" method="POST" name="appCheckList_form" commandName="appCheckListForm" action="appCheckListForm.html">
       	   	     <input type="hidden" name="actionType" value="<%=actionType%>"/>       
                   
                 <form:hidden id="checkId" path="checkId"  />
                 <fieldset > 
                   <legend><span class="AccountCreateTableHeader">
                    
                       <%=ActionTitle%> Application Check List 
                    
                     </span> </legend>
                     
      		        	  <table>
      		        	  
        				   
		      			 <tr><td>
		      			 	<span style="font-weight:bold; color:blue;font-size:13px;"><font color="red">*</font>Check List Title(500 characters):</span><br>
                          
                             <form:textarea path="title" id="title"  cssClass="appCheckListTitle"/><br/>
     		          	
		      			 
		      			 </td></tr>
		      			
		      			  <tr><td>
		      			   <br>	<span style="font-weight:bold; color:blue;font-size:13px;">Check List Description:</span><br>
		      			   <form:textarea path="note" id="note" cssClass="appCheckListNote"/><br/>
		      			 </td></tr>
		      			 
		      			 
		      			</table> 	
		      			
    			    	<div style="margin-left:160px;">
    			    		<table style="border:0px;" >    			  
		      			   
  						 		 <tr><td>
  						 		     <input type="button" value=" <%=submitAction%>" class="buttonImage" onClick="javascript:submitform();">
   								 </td>
  								   <td>&nbsp;</td>
  								    <td>&nbsp;</td>
  								     <td>&nbsp;</td>
  								   <td>
               		 				 <input type="button"  value="Close" class="buttonImage" onClick="javascript: refreshParent();">
  								 </td></tr>
  							</table>	
		      			</div> 
		      			   
    			 </fieldset>  	
    			  </form:form>   
 		          
           	 	 <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/formUtils.js"> </script>  
			  <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/appCheckListForm.js"> </script>   
        
   
   
