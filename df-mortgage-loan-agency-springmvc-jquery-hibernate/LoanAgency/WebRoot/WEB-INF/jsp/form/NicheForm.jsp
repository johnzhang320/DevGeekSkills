<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ page import="com.loan.agent.services.Constant" %>	
 <style>
<!--
  .nicheTitle {
  	width:510px;
  	height:70px;
  	border : #000000 solid 1px; 
  	overflow:scroll-y;
  }
   .nicheNote {
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
	    	if (Constant.ACTION_NICHE_EDIT.equalsIgnoreCase(actionType)) {
	    		ActionTitle="Edit the ";
	    	}
	    	if ( Constant.ACTION_NICHE_DELETE.equalsIgnoreCase(actionType)) {		
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
       	   <form:form  id="niche_form" method="POST" name="niche_form" commandName="nicheForm" action="nicheForm.html">
       	         <input type="hidden" name="actionType" value="<%=actionType%>"/>            
                 <form:hidden id="nicheId" path="nicheId"  />
                 <fieldset > 
                   <legend><span class="AccountCreateTableHeader">
                    
                       <%=ActionTitle%> Niche Loan Program 
                    
                     </span> </legend>
                     
      		        	  <table>
      		        	  
        				   
		      			 <tr><td>
		      			 	<span style="font-weight:bold; color:blue;font-size:13px;"><font color="red">*</font>Niche Title(500 characters):</span><br>
                             <form:textarea path="nicheTitle" id="nicheTitle"  cssClass="nicheTitle"/><br/>
     		          	
		      			 
		      			 </td></tr>
		      			
		      			  <tr><td>
		      			   <br>	<span style="font-weight:bold; color:blue;font-size:13px;">Niche Description:</span><br>
		      			   <form:textarea path="nicheNote" id="nicheNote" cssClass="nicheNote"/><br/>
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
			  <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/nicheForm.js"> </script>   
        
   
   
