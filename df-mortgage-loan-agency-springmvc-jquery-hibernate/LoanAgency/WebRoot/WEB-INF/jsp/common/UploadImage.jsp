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
          <div id="cProgressSamll" style="display:none;top:150px; left:200px" class="progress_dialog">      
			 <img style="margin-left:40%;margin-top:20%;" src="<%=imagePath%>animateCircle.gif" />
			</div>              
            
       	   <form:form   method="POST" name="emailServerForm" commandName="emailServerForm" enctype="multipart/form-data" target="_self">
       	                    
               <form:hidden id="actionType" path="actionType" />
                 <fieldset > 
                   <legend><span class="AccountCreateTableHeader">Please Upload Image File </span> </legend>                   
    
      		        	  <table>      		        	  
        				    <logic:present name="emailListErrorMessage" >
        				      <tr><td>
                          		  <span style="font-weight:bold; color:red; font-size:15px;"><bean:write name="emailListErrorMessage"/></span>
                          	   </td></tr>
                     		 </logic:present>
                     		 
		      			 <tr><td>
		      			 	<label id="pictureContentLabel">File Name:</label>
		      			 	<form:input path="filename"  size="80"  maxlength="200" /><br/> 
		      			 	<label id="pictureContentLabel">File Size:</label>
		      			 	<form:input path="filename"  size="80"  maxlength="200" /><br/> 
		      			 	<label id="pictureContentLabel">Upload Image File :</label>
                            <input id="fileContent" type="file" name="fileContent"  style="width:340px;" onChange="javascript:uploadImage();" />
                           
 		      			 </td></tr>
		      			 <logic:present name="imageFilename"> 
		      		     <tr><td>
		      		     	<label id="pictureContentLabel">Photo Picture:</label>
		      		         <img width="140" height="170" alt="" id="agentImage" src="<%=request.getContextPath()%>/previewPicture.html" />    
 		      		     </td></tr>
 		      			 
 		      			 </logic:present>
		      			 
		      			</table> 	
		      				
    			    	 	<a  href="javascript:self.close();"><img style="border:0px;padding-left:160px;padding-top:20px;padding-bottom:20px;" 
    			    		src="<%=request.getContextPath()%>/images/formImages/b_close.png" 
    			    		title="Close Current Window" width="80" height="30" />
    			    		
    			    	   </a>   
    			    	   <br>
    			    	 <br>
    			 </fieldset>  	
    			  </form:form> 
           	 	 <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/formUtils.js"> </script>  
			  	 <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/uploadImage.js"> </script>  
		 	 
   					
    