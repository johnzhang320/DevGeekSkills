<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ page import="com.loan.agent.services.Constant" %>	
 
 
	<%
	     String connectionStatus = (String) request.getSession().getAttribute(Constant.EMAIL_SERVER_CONNECTION_STATUS);
	     String imagePath =request.getContextPath()+"/images/layoutImages/";
	       int line=0;
	%>        
	  
 
     <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common/tablescroll.css" />
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/utils/tablescroll.js"> </script> 
 
  
 <script  type="text/javascript">
/*<![CDATA[*/

jQuery(document).ready(function($)
{
	$('#thetable6').tableScroll({height:500});
  
});

/*]]>*/
 
	function SelectFirstName(firstNamePtr) {
		document.getElementById("actionType").value="chosenFirstNamePtr";
		document.getElementById("firstNamePtr").value=firstNamePtr;
		document.forms[0].submit();
	}
	function SelectEmail(emailPtr) {
		document.getElementById("actionType").value="chosenEmailPtr";
		document.getElementById("emailPtr").value=emailPtr;
		document.forms[0].submit();
	}
 
</script>  
          <div id="errorBox" class="errorblock"></div> 
           <div id="cProgressSamll" style="display:none;" style="algin:center;" class="progress_dialog">      
			 <img style="margin-left:30%;margin-top:20%;" src="<%=imagePath%>animateCircle.gif" />
			</div>        
            
       	   <form:form   method="POST" name="emailServerForm" commandName="emailServerForm" enctype="multipart/form-data" target="_self">
       	                    
               <form:hidden id="actionType" path="actionType" />
               <form:hidden id="firstNamePtr" path="firstNamePtr" />
               <form:hidden id="emailPtr" path="emailPtr" />
                   <fieldset > 
                   <legend><span class="AccountCreateTableHeader">Please Upload Email List File  </span> </legend>
                       <label style="width:300px;">Move your mouse over question mark to view help</label>
    
      		        	  <table>
      		        	  
        				    <logic:present name="emailListErrorMessage" >
        				      <tr><td>
                          		  <span style="font-weight:bold; color:red; font-size:15px;"><bean:write name="emailListErrorMessage"/></span>
                          	   </td></tr>
                     		 </logic:present>
		      			 <tr><td>
		      			 	<label id="pictureContentLabel" style="margin-left:-20px;"><font color="red">*</font>Upload Email List File:</label>
                            <input id="fileContent" type="file" name="fileContent" size="65" maxlength="280" style="width:300px;" onChange="javascript:uploadDocFile();" /> 
                            	     
	      			     </td></tr>
		      			 	<logic:present name="EmailDynamicList" >
		      			 	 <tr>
		      			     	 <td>
		      			 	      	<jsp:include page="SelectEmailAndFirstName.jsp" flush=true />
		      			      	</td>
		      				 </tr>
		      		     </logic:present>
		      			 <logic:present name="SimpleEmailLineList" >
		      			 	 <tr>
		      			     	 <td>
		      			 	        <table id="thetable6"  border="0" cellpadding="30"  >
                             		<thead>
                          			<tr>
                            	
                                 	    <td> <font class="tstitle">No.</font> </td>    	  		 
                           		 	    <td ><font class="tstitle">View and Change Email List</font></td>
                           		    	<td><font class="tstitle">Edit</font></td>
                           		        <td><font class="tstitle">Delete</font></td>
                               	      </tr>   
                         	          </thead>
                         	        	<tbody> 	   	
                         	  	           <logic:iterate id="review" name="SimpleEmailLineList" type="com.email.list.reader.SimpleEmailLineVO" scope="session" indexId="rowIndex"> 
                         	      			<%                  	      			 
                         	      			 if (line%2==0) {%>
                              					<tr class="rowwhite">    
                                	 		<%} else { %>
                                	    		<tr class="rowshade"> 
                                	  		 <%}
                                	   		 line++;        	    
                                	   		 %>
                     						<td><bean:write name="review" property="lineId"/></td>
                            	  		    <td><bean:write name="review" property="firstName"/></td>
                            	     		<td><bean:write name="review" property="emailAddress"/></td>
                              	   			<td>
                              	   			 <input type="button" class="tinybutton" value="Edit" onClick="javascript:popUpBase('/emailListProcessChildForm.html?actionType=Edit&lineId='+'<bean:write name="review" property="lineId"/>','670','700');">
                            	   			</td> 
                            	   			<td>
                              	   			 <input type="button" class="tinybutton" value="Delete" onClick="javascript:popUpBase('/emailListProcessChildForm.html?actionType=Delete&lineId='+'<bean:write name="review" property="lineId"/>','670','700');">
                            	   			</td> 
                            			</tr>
                            		 
                                  </logic:iterate>
                                 
                                </tbody>
                         </table>
                            
                         </logic:present>
 		      			 
		      			</table> 	
		      		      <input type="button" value="Apply Email List" class="buttonImage"   onClick="javascript:applyEmailList();">
  	         			  <input type="button" value="Save Email List" class="buttonLeft"   onClick="javascript:downloadEmailContent();">
  	              		  <input type="button" value="Close" class="buttonLeft"   onClick="javascript:self.reloadParentCloseSelf();">
 		      			 
		      			   
    			 </fieldset>  	
    			  </form:form> 
    			  
    			  
    			    
	 			
 		          
 		          
           	 	 <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/formUtils.js"> </script>  
			  <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/uploadDocFile.js"> </script>   
        
   
   